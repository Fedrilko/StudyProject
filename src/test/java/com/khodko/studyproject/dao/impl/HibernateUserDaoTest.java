package com.khodko.studyproject.dao.impl;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContextTest.xml"})
public class HibernateUserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional
    @Rollback(true)
    public void create_ShouldAddNewEntry() {
        User user = new User("petr", "root", "petro@gmail.com", "Petr",
                "Petrov", Date.valueOf("1989-10-16"), null);
        userDao.create(user);
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        final int numberOfEntriesAfterInsertion = 3;
        assertEquals(numberOfEntriesAfterInsertion, users.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldUpdateExistingEntry() {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User u where u.login = 'fedor'").getSingleResult();
        String newFirstName = "Petr";
        user.setFirstName(newFirstName);
        userDao.update(user);
        assertEquals(newFirstName, session.get(User.class, user.getId()).getFirstName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldRemoveExistingEntry() {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User u where u.login = 'fedor'").getSingleResult();
        userDao.remove(user);
        List<User> users = session.createQuery("from User").list();
        final int numberOfEntriesAfterRemoval = 1;
        assertEquals(numberOfEntriesAfterRemoval, users.size());
    }

    @Test
    @Rollback(true)
    public void shouldReturnQtyOfEntries() {
        List<User> users = userDao.findAll();
        final int expectedNumberOfEntries = 2;
        assertEquals(expectedNumberOfEntries, users.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldReturnListOfZeroSizeIfTableIsEmpty() {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("DELETE FROM users").executeUpdate();
        List<User> users = session.createQuery("from User").list();
        assertEquals(users.size(), 0);
    }

    @Test
    public void shouldReturnSpecifiedUserWithSpecifiedLogin() {
        assertNotNull(userDao.findByLogin("fedor"));
    }

    @Test
    public void shouldReturnNullIfUserWithSpecifiedLoginDoesNotExist() {
        assertNull(userDao.findByLogin("oleg"));
    }

    @Test
    public void shouldReturnSpecifiedUserWithSpecifiedEmail() {
        assertNotNull(userDao.findByEmail("fedorkhodko@gmail.com"));
    }

    @Test
    public void shouldReturnNullIfUserWithSpecifiedEmailDoesNotExist() {
        assertNull(userDao.findByEmail("oleg@gmail.com"));
    }

    @Test
    public void shouldReturnUserWithRoleAdmin() {
        User user = userDao.findByEmail("fedorkhodko@gmail.com");
        final String roleName = "Admin";
        assertEquals(roleName, user.getRole().getName());
    }
}

