package com.khodko.studyproject.dao.impl;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.khodko.studyproject.dao.RoleDao;
import com.khodko.studyproject.models.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class HibernateRoleDao implements RoleDao {

	private final SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Role role) {
    	Session session = sessionFactory.getCurrentSession();
    	session.save(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
   	    if(role.getId() == 0) throw new IllegalArgumentException("Transient object was passed as an argument");
    	Session session = sessionFactory.getCurrentSession();
    	session.update(role);
    }

    @Override
    @Transactional
    public void remove(Role role) {
        if(role.getId() == 0) throw new IllegalArgumentException("Transient object was passed as an argument");
    	Session session = sessionFactory.getCurrentSession();
    	session.delete(role);
    }

    @Override
    @Transactional
    public Role findByName(String name) {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery("from Role r where r.name = :name");
    	query.setParameter("name", name);
        List<Role> list = query.list();
        if(list.size() == 0) return null;
        else return list.get(0);
    }
}
