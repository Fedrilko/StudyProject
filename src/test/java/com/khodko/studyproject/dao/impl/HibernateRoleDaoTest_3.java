package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.models.Role;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

public class HibernateRoleDaoTest_3 extends DBTestCase {

    public HibernateRoleDaoTest_3(String name) {
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


}