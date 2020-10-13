package com.khodko.studyproject.dao.impl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//TODO: add handling of empty result (return null issue)
//TODO: add handling of invalid parameters
//TODO: add cascade relationship

@Component
public class HibernateRoleDao implements RoleDao {
	@Autowired
	SessionFactory sessionFactory;
	
   	@Override
    @Transactional
    public void create(Role role) {
    	Session session = sessionFactory.getCurrentSession();
    	session.save(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
   	    if(role.getId() == 0) throw new IllegalArgumentException("Transient object is passed as an argument");
    	Session session = sessionFactory.getCurrentSession();

    	//Next two lines produce two requests to the db
//    	Role existingRole = session.get(Role.class, role.getId());
//    	existingRole.setName(role.getName());
        //Refactoring:
        Query query = session.createQuery("update Role r set r.name = :name where r.id = :id");
        query.setParameter("name", role.getName());
        query.setParameter("id", role.getId());

    }

    @Override
    @Transactional
    public void remove(Role role) {
        if(role.getId() == 0) throw new IllegalArgumentException("Transient object is passed as an argument");
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
