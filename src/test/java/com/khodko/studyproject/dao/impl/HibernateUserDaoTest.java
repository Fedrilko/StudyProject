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
        //given
        User user = new User("petr", "root", "petro@gmail.com", "Petr",
                "Petrov", Date.valueOf("1989-10-16"), null);
        //when
        userDao.create(user);
        //then
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        final int numberOfEntriesAfterInsertion = 3;
        assertEquals(numberOfEntriesAfterInsertion, users.size());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldUpdateExistingEntry() {
        //given
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User u where u.login = 'fedor'").getSingleResult();
        String newFirstName = "Petr";
        user.setFirstName(newFirstName);
        //when
        userDao.update(user);
        //then
        assertEquals(newFirstName, session.get(User.class, user.getId()).getFirstName());
    }

    @Test
    @Transactional
    @Rollback(true)
    public void shouldRemoveExistingEntry() {
        //given
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("from User u where u.login = 'fedor'").getSingleResult();
        //when
        userDao.remove(user);
        //then
        List<User> users = session.createQuery("from User").list();
        final int numberOfEntriesAfterRemoval = 1;
        assertEquals(numberOfEntriesAfterRemoval, users.size());
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
    @Transactional
    @Rollback(true)
    public void shouldReturnListOfZeroSizeIfTableIsEmpty() {
        Session session = sessionFactory.getCurrentSession();
        session.createNativeQuery("DELETE FROM sp_users").executeUpdate();
        List<User> users = session.createQuery("from User").list();
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
        assertNotNull(userDao.findByEmail("fedorkhodko@gmail.com"));
    }

    @Test
    public void shouldReturnNullIfUserWithSpecifiedEmailDoesNotExist() {
        assertNull(userDao.findByEmail("oleg@gmail.com"));
    }

    @Test
    public void shouldReturnUserWithRoleAdmin() {
        //when
        User user = userDao.findByEmail("fedorkhodko@gmail.com");
        //then
        final String roleName = "Admin";
        assertEquals(roleName, user.getRole().getName());
    }
}

