package com.khodko.studyproject.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.khodko.studyproject.models.Role;
import com.khodko.studyproject.models.User;
import org.hibernate.query.Query;

import java.util.Date;

public class DaoDemo {

	public static void main(String[] args) {
		System.out.println("lol");
		SessionFactory sf = new Configuration().configure("hibernate.cfg2.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class)
				.buildSessionFactory();
		
		Session s = sf.openSession();

		//save user
		s.beginTransaction();
		User u  = new User("ivan", "root", "lol@kek.com", "Ivan",
				"Ivanov", new Date(1989, 10,16), getRole("Admin", sf));
		s.save(u);
		s.getTransaction().commit();


		s.close();
		sf.close();
	}

	public static Role getRole(String roleName, SessionFactory sf) {
		try(Session s = sf.openSession()) {
			Query q = s.createQuery("from Role r where r.name = :name");
			q.setParameter("name", roleName);
			return (Role)q.getSingleResult();
		}
	}

}
