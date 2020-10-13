package com.khodko.studyproject.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;
import org.springframework.stereotype.Component;

//TODO: add handling of empty result (return null issue)
//TODO: add handling of invalid parameters
//TODO: add cascade relationship

@Component
public class HibernateUserDao implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
    @Override
    @Transactional
    public void create(User user) {
    	Session session = sessionFactory.getCurrentSession();
    	session.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        if(user.getId() == 0) throw new IllegalArgumentException("Transient object is passed as an argument");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("update User u set u.firstName = :firstName," +
                "u.lastName = :lastName, u.login = :login, u.password = :password," +
                "u.email = :email where u.id = :id");
        query.setParameter("firstName", user.getFirstName());
        query.setParameter("lastName", user.getLastName());
        query.setParameter("login", user.getLogin());
        query.setParameter("password", user.getPassword());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());

//        User existingUser = session.get(User.class, user.getId());
//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getFirstName());
//        existingUser.setLogin(user.getLogin());
//        existingUser.setPassword(user.getPassword());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setBirthDate(user.getBirthDate());
    }

    @Override
    @Transactional
    public void remove(User user) {
        if(user.getId() == 0) throw new IllegalArgumentException("Transient object is passed as an argument");
        Session session = sessionFactory.getCurrentSession();
        session.remove(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();//returns empty list if nothing is found
    }

    @Override
    @Transactional
    public User findByLogin(String login) { //login s/b unique in the database
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("from User u where u.login = :login");
    	query.setParameter("login", login);
        return (User)query.getSingleResult();
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);
        return (User)query.getSingleResult();
    }
}
