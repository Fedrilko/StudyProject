package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.models.Role;
import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//import static org.junit.Assert.*;

//@RunWith(JUnit4.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class HibernateRoleDaoTest extends DBTestCase {
//    @Autowired
//    private RoleDao roleDao;
//    @Autowired
//    private SessionFactory sessionFactory;

    public HibernateRoleDaoTest(String name) {
        super(name);
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:mysql://localhost:3306/study_project_test?useUnicode=true&serverTimezone=GMT%2b8" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root" );
        System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "root" );
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("dataset.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.REFRESH;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }


    @Test
    public void shouldSaveNewRoleToTheDatabase() throws Exception {
        Role newRole = new Role("Guest");
        getConnection().getConnection().createStatement().executeUpdate("insert into roles (name) values('Guest')");
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_adding.xml"));
        ITable expectedTable = expectedData.getTable("roles");
        IDataSet actualData = getConnection().createDataSet();
        ITable actualTable = actualData.getTable("roles");
        assertEquals(expectedTable, actualTable);
    }

//    @Test
//    public void shouldSaveNewRoleToTheDatabase() throws Exception {
//        Role newRole = new Role("Guest");
//        roleDao.create(newRole);
//        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
//                Thread.currentThread().getContextClassLoader()
//                        .getResourceAsStream("dataset_after_adding.xml"));
//        ITable expectedTable = expectedData.getTable("roles");
//        IDataSet actualData = getConnection().createDataSet();
//        ITable actualTable = actualData.getTable("roles");
//        assertEquals(expectedTable, actualTable);
//    }

//    @Test
//    @Transactional
//    @Rollback(true)
//    public void shouldAddNewEntry() {
//        //given
//        Role newRole = new Role("Guest");
//        //when
//        roleDao.create(newRole);
//        //then
//        Session session = sessionFactory.getCurrentSession();
//        List<Role> roles = session.createQuery("from Role").list();
//        final int expectedNumberOfEntries = 3;
//        assertEquals(expectedNumberOfEntries, roles.size());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void shouldUpdateExistingEntry() {
//        //given
//        Session session = sessionFactory.getCurrentSession();
//        Role role = (Role) session.createQuery("from Role r where r.name = 'Admin'").getSingleResult();
//        final String newRoleName = "Guest";
//        role.setName(newRoleName);
//        //when
//        roleDao.update(role);
//        //then
//        assertEquals(newRoleName, session.get(Role.class, role.getId()).getName());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void shouldRemoveExistingEntry() {
//        //given
//        Session session = sessionFactory.getCurrentSession();
//        Role role = (Role) session.createQuery("from Role r where r.name = 'Admin'").getSingleResult();
//        //when
//        roleDao.remove(role);
//        //then
//        List<Role> roles = session.createQuery("from Role").list();
//        final int numberOfEntriesAfterRemoval = 1;
//        assertEquals(numberOfEntriesAfterRemoval, roles.size());
//    }
//
//    @Test
//    public void shouldReturnSpecifiedRole() {
//        assertNotNull(roleDao.findByName("Admin"));
//    }
//
//    @Test
//    public void shouldReturnNull() {
//        assertNull(roleDao.findByName("Guest"));
//    }

}