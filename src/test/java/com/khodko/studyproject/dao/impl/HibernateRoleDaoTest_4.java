package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.models.Role;
import org.dbunit.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

public class HibernateRoleDaoTest_4 {

    @Test
    public void shouldSaveNewRoleToTheDatabase() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getClassLoader()
                .getResourceAsStream("dataset.xml"));
        IDatabaseTester tester = new JdbcDatabaseTester("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://localhost:3306/study_project_test?useUnicode=true&serverTimezone=GMT%2b8",
                "root", "root");

        Role newRole = new Role("Guest");
        tester.getConnection().getConnection().createStatement().executeUpdate("insert into roles (name) values('Guest')");
        IDataSet expectedData = new FlatXmlDataSetBuilder().build(
                Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("dataset_after_adding.xml"));
        ITable expectedTable = expectedData.getTable("roles");
        IDataSet actualData = tester.getConnection().createDataSet();
        ITable actualTable = actualData.getTable("roles");
        Assertion.assertEquals(expectedTable, actualTable);
    }


}