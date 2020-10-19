package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.models.Role;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class HibernateRoleDaoTest extends DBTestCase {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    protected IDataSet getDataSet() throws Exception {
        return null;
    }


//    @Test
//    @Transactional
//    @Rollback(true)
//    public void shouldAddNewEntry() {
//        Role newRole = new Role("Guest");
//        roleDao.create(newRole);
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
//        Session session = sessionFactory.getCurrentSession();
//        Role role = (Role) session.createQuery("from Role r where r.name = 'Admin'").getSingleResult();
//        final String newRoleName = "Guest";
//        role.setName(newRoleName);
//        roleDao.update(role);
//        assertEquals(newRoleName, session.get(Role.class, role.getId()).getName());
//    }
//
//    @Test
//    @Transactional
//    @Rollback(true)
//    public void shouldRemoveExistingEntry() {
//        Session session = sessionFactory.getCurrentSession();
//        Role role = (Role) session.createQuery("from Role r where r.name = 'Admin'").getSingleResult();
//        roleDao.remove(role);
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