package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.models.Role;
import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.awt.color.ICC_Profile;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class HibernateRoleDaoTest {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SessionFactory sessionFactory;

    private IDatabaseTester tester;

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
        Role newRole = new Role("Guest");
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_adding.xml"));
        ITable expectedTable = expectedData.getTable("sp_roles");
        //when
        roleDao.create(newRole);
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable("sp_roles");
        //then
        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
    }

    @Test
    public void shouldUpdateExistingEntry() throws Exception {
        //given
        Role role = new Role("Guest");
        role.setId(1l);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_updating.xml"));
        ITable expectedTable = expectedData.getTable("sp_roles");
        //when
        roleDao.update(role);
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable("sp_roles");
        //then
        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
    }

    @Test
    public void shouldRemoveExistingEntry() throws Exception {
        //given
        Role role = new Role("User");
        role.setId(2l);
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_removing.xml"));
        ITable expectedTable = expectedData.getTable("sp_roles");
        //when
        roleDao.remove(role);
//        tester.getConnection().getConnection().createStatement().executeUpdate("delete from sp_roles where id = '2'");
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable("sp_roles");
        System.out.println("row count: " + actualTable.getRowCount());
        //then
        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
    }


    @Test
    public void shouldReturnSpecifiedRole() {
        assertNotNull(roleDao.findByName("Admin"));
    }

    @Test
    public void shouldReturnNull() {
        assertNull(roleDao.findByName("Guest"));
    }

}