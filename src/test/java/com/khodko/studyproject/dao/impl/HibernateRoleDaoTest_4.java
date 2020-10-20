package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.models.Role;
import junit.framework.TestCase;
import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

public class HibernateRoleDaoTest_4 {


    @Test
    public void shouldSaveNewRoleToTheDatabase() throws Exception {
        IDatabaseTester tester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/test",
                "root", "root");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("dataset_person.xml"));

        tester.setDataSet(dataSet);
        tester.setSetUpOperation(DatabaseOperation.REFRESH);
        System.out.println(tester.getSetUpOperation());
        tester.onSetup();
//        Role newRole = new Role("Guest");
//        tester.getConnection().getConnection().createStatement().executeUpdate("insert into roles (name) values('Guest')");
        tester.getConnection().getConnection().createStatement().executeUpdate("insert into person (name) values('Fedor')");
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_person_after_adding.xml"));
        ITable expectedTable = expectedData.getTable("person");
        System.out.println(expectedTable.getRowCount());
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable("person");
        System.out.println(actualTable.getRowCount());
        Assertion.assertEquals(expectedTable, actualTable);
        tester.setTearDownOperation(DatabaseOperation.TRUNCATE_TABLE);
        tester.onTearDown();
    }

}