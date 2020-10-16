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
public class HibernateUserDao implements UserDao {
	
	private final SessionFactory sessionFactory;
	
    @Override
    @Transactional
    public void create(User user) {
    	Session session = sessionFactory.getCurrentSession();
    	session.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        if(user.getId() == 0) throw new IllegalArgumentException("Transient object was passed as an argument");
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    @Transactional
    public void remove(User user) {
        if(user.getId() == 0) throw new IllegalArgumentException("Transient object was passed as an argument");
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
        List<User> list = query.list();
        if(list.size() == 0) return null;
        else return list.get(0);
    }

    @Override
    @Transactional
    public User findByEmail(String email) { //email s/b unique in the database
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);
        List<User> list = query.list();
        if(list.size() == 0) return null;
        else return list.get(0);
    }
}
