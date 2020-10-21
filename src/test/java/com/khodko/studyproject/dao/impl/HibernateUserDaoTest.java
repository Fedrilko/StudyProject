package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.Role;
import com.khodko.studyproject.models.User;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class HibernateUserDaoTest {

    @Autowired
    private UserDao userDao;

    private IDatabaseTester tester;

    private static final String TABLE_NAME = "sp_users";
    private static final String[] IGNORE = {"id"};
    private static final String EMAIL = "fedorkhodko@gmail.com";

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester("org.h2.Driver",
                "jdbc:h2:mem:study_project_test;DB_CLOSE_DELAY=-1",
                "root", "root");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("dataset.xml"));
        tester.setSetUpOperation(DatabaseOperation.REFRESH);
        tester.setDataSet(dataSet);
        tester.onSetup();
    }

    @After
    public void cleanUp() throws Exception {
        tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        tester.onTearDown();
    }

    @Test
    public void shouldAddNewEntry() throws Exception {
        //given
        Role role = new Role("Admin");
        role.setId(1l);
        User newUser = new User("petr", "root", "petro@gmail.com", "Petr",
                "Petrov", Date.valueOf("1989-10-16"), role);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_adding.xml"));
        ITable expectedTable = expectedData.getTable(TABLE_NAME);
        //when
        userDao.create(newUser);
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable(TABLE_NAME);
        //then
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, IGNORE);
    }

    @Test
    public void shouldUpdateExistingEntry() throws Exception {
        //given
        Role role = new Role("User");
        role.setId(2l);
        User existingUser = new User("fedor", "root", EMAIL, "Fedor",
                "Khodko", Date.valueOf("1989-10-16"), role);
        existingUser.setId(1l);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_updating.xml"));
        ITable expectedTable = expectedData.getTable(TABLE_NAME);
        //when
        userDao.update(existingUser);
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable(TABLE_NAME);
        //then
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, IGNORE);
    }

    @Test
    public void shouldRemoveExistingEntry() throws Exception {
        //given
        Role role = new Role("User");
        role.setId(2l);
        User existingUser = new User("ivan", "root", "ivan@gmail.com", "Ivan",
                "Lykhosherst", Date.valueOf("1987-09-02"), role);
        existingUser.setId(2l);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_removing.xml"));
        ITable expectedTable = expectedData.getTable(TABLE_NAME);
        //when
        userDao.remove(existingUser);
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable(TABLE_NAME);
        System.out.println("row count: " + actualTable.getRowCount());
        //then
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, IGNORE);
    }

    @Test
    @Rollback(true)
    public void shouldReturnQtyOfEntries() {
        //when
        List<User> users = userDao.findAll();
        //then
        final int expectedNumberOfEntries = 2;
        assertEquals(expectedNumberOfEntries, users.size());
    }

    @Test
    public void shouldReturnListOfZeroSizeIfTableIsEmpty() throws Exception {
        //given
        tester.getConnection().getConnection().createStatement().executeUpdate("delete from sp_users");
        //when
        List<User> users = userDao.findAll();
        //then
        assertEquals(users.size(), 0);
    }

    @Test
    public void shouldReturnUserWithSpecifiedLogin() {
        assertNotNull(userDao.findByLogin("fedor"));
    }

    @Test
    public void shouldReturnNullIfUserWithSpecifiedLoginDoesNotExist() {
        assertNull(userDao.findByLogin("oleg"));
    }

    @Test
    public void shouldReturnUserWithSpecifiedEmail() {
        assertNotNull(userDao.findByEmail(EMAIL));
    }

    @Test
    public void shouldReturnNullIfUserWithSpecifiedEmailDoesNotExist() {
        assertNull(userDao.findByEmail("oleg@gmail.com"));
    }

    @Test
    public void shouldReturnUserWithRoleAdmin() {
        //when
        User user = userDao.findByEmail(EMAIL);
        //then
        final String roleName = "Admin";
        assertEquals(roleName, user.getRole().getName());
    }
}

