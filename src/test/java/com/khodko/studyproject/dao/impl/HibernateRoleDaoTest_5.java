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
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class HibernateRoleDaoTest_5 {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SessionFactory sessionFactory;

    private IDatabaseTester tester;

    @Before
    public void setUp() throws Exception{
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
    public void cleanUp() throws Exception{
        tester.setTearDownOperation(DatabaseOperation.DELETE_ALL);
        tester.onTearDown();
    }


    @Test
    public void shouldSaveNewRoleToTheDatabase() throws Exception {

        Role newRole = new Role("Guest");
        roleDao.create(newRole);
//        tester.getConnection().getConnection().createStatement().executeUpdate("insert into roles (name) values('Guest')");
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_adding.xml"));
        ITable expectedTable = expectedData.getTable("sp_roles");

        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable("sp_roles");
        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, ignore);
    }

}