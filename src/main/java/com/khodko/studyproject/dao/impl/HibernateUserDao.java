package com.khodko.studyproject.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.models.User;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class HibernateUserDao implements UserDao {
	
	private final SessionFactory sessionFactory;
	
    @Override
    public void create(User user) {
    	Session session = sessionFactory.getCurrentSession();
    	session.save(user);
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void remove(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(user);
    }

    @Override
    public List<User> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();//returns empty list if nothing is found
    }

    @Override
    public User findByLogin(String login) { //login s/b unique in the database
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("from User u where u.login = :login");
    	query.setParameter("login", login);
        return (User)query.uniqueResult();
    }

    @Override
    public User findByEmail(String email) { //email s/b unique in the database
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);
        return (User)query.uniqueResult();
    }
}
