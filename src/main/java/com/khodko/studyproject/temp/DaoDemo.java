package com.khodko.studyproject.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.khodko.studyproject.models.Role;
import com.khodko.studyproject.models.User;

public class DaoDemo {

	public static void main(String[] args) {
		System.out.println("lol");
		SessionFactory sf = new Configuration().configure("hibernate.cfg2.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class)
				.buildSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		User u  = new User("fredor", "root", "lol@kek.com", "Fedor", "Khodko", null, null);
		s.save(u);
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

}
