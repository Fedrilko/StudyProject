package com.khodko.studyproject.temp;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.dao.impl.HibernateUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.khodko.studyproject.models.Role;
import com.khodko.studyproject.models.User;
import org.hibernate.query.Query;

import java.util.Date;

public class DaoDemo {

	public static void main(String[] args) {
		SessionFactory sf = new Configuration().configure("hibernate.cfg2.xml")
				.addAnnotatedClass(User.class)
				.addAnnotatedClass(Role.class)
				.buildSessionFactory();

		Session s = sf.openSession();

		//save user
		s.beginTransaction();
		User u  = new User("ivan", "root", "lol@kek.com", "Ivan",
				"Ivanov", new Date(1989, 10,16), null);
		s.save(u);
		u.setFirstName("Ivan");
		u.setLastName("Ivanov");
//		System.out.println(s.get(User.class, u.getId()).equals(u));//cached object returns
//		u.setLastName("Petrov");
//		u.setPassword("lol");
//		User u2 = new User();
//		u2.setId(u.getId());
//		s.save(u2);
		s.getTransaction().commit();


		//update user
//		s.beginTransaction();
//		Query query = s.createQuery("from User u where u.login = 'fredor'");
//		User user = (User)query.getSingleResult();
//		System.out.println(user);
//		s.getTransaction().commit();




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
