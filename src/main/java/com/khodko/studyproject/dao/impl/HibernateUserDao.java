package com.khodko.studyproject.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;

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

    }

    @Override
    @Transactional
    public void remove(User user) {

    }

    @Override
    @Transactional
    public List<User> findAll() {
        return null;
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("from User u where u.login = :login");
    	query.setParameter("login", login);
        return (User)query.getSingleResult();
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return null;
    }
}
