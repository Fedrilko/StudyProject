package com.khodko.studyproject.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.models.Role;

public class HibernateRoleDao implements RoleDao {
	
	SessionFactory sessionFactory;
	
    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
    @Transactional
    public void create(Role role) {
    	Session session = sessionFactory.getCurrentSession();
    	session.save(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
    	Session session = sessionFactory.getCurrentSession();
//    	role = session.get(Role.class, role.getId());
    	//WTF is going on here? Why should we do this?
    }

    @Override
    @Transactional
    public void remove(Role role) {
    	Session session = sessionFactory.getCurrentSession();
    	session.delete(role);
    }

    @Override
    @Transactional
    public Role findByName(String name) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("from Role r where r.name = :name");
    	query.setParameter("name", name);
        return (Role)query.getSingleResult();
    }
}
