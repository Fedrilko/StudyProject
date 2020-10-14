package com.khodko.studyproject.temp;

import com.khodko.studyproject.dao.UserDao;
import com.khodko.studyproject.dao.impl.HibernateUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.khodko.studyproject.models.Role;
import com.khodko.studyproject.models.User;
import org.hibernate.query.Query;

import java.sql.Date;

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
				"Ivanov", Date.valueOf("1989-10-16"), null);
		s.save(u);
//		u.setFirstName("Ivan");
//		u.setLastName("Ivanov");
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


		//update role
//		s.beginTransaction();
//		Role role = new Role("User");
//		role.setId(1);
//		Query query = s.createQuery("update Role r set r.name = :name where r.id = :id");
//		query.setParameter("name", role.getName());
//		query.setParameter("id", role.getId());
//		query.executeUpdate();
//		s.getTransaction().commit();


		//update user
//		s.beginTransaction();
//		User user  = new User("fedor", "root", "ssdfsdfdfsdfdf@kek.com", "Fedor",
//				"Fedorov", new Date(1989, 10,16), null);
//		user.setId(23);
//		Query query = s.createQuery("update User u set u.firstName = :firstName," +
//				"u.lastName = :lastName, u.login = :login, u.password = :password," +
//				"u.email = :email where u.id = :id");
//		query.setParameter("firstName", user.getFirstName());
//		query.setParameter("lastName", user.getLastName());
//		query.setParameter("login", user.getLogin());
//		query.setParameter("password", user.getPassword());
//		query.setParameter("email", user.getEmail());
//		query.setParameter("id", user.getId());
//		query.executeUpdate();
//		s.getTransaction().commit();


//		s.beginTransaction();
//		User u = new User();
//		u.setId(23);
//		s.save(u);
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
