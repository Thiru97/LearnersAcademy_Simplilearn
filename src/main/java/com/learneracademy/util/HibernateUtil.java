package com.learneracademy.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.learneracademy.entity.MyClass;
import com.learneracademy.entity.Student;
import com.learneracademy.entity.Subject;
import com.learneracademy.entity.Teacher;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;

	private static SessionFactory getSessionFactory() {

		if (sessionFactory != null) {
			return sessionFactory;
		}

		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml").addAnnotatedClass(MyClass.class)
					.addAnnotatedClass(Student.class).addAnnotatedClass(Subject.class).addAnnotatedClass(Teacher.class);
			;

			System.out.println("Hibernate Configuration loaded");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			System.out.println("Hibernate serviceRegistry created");

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			sessionFactory.openSession();

			return sessionFactory;
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}

	}

	@SuppressWarnings("unused")
	public static Session getSession() {
		Session session = null;
		if (session != null) {
			return session;
		} else {
			return getSessionFactory().openSession();
		}
	}
}
