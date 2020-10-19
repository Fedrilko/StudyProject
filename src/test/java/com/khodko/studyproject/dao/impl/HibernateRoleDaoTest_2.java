package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.config.DBUnitConfig;
import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.models.Role;
import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//import static org.junit.Assert.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class HibernateRoleDaoTest_2 extends DBUnitConfig {
//    @Autowired
//    private RoleDao roleDao;
//    @Autowired
//    private SessionFactory sessionFactory;

    public HibernateRoleDaoTest_2(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        beforeData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset.xml"));

        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    @Test
    public void testSave() throws Exception {
//        Role newRole = new Role("Guest");
        tester.getConnection().getConnection().createStatement().executeUpdate("insert into roles (name) values('Guest')");
//        roleDao.create(newRole);

        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_adding.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();

        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData, actualData, "roles", ignore);
    }


}