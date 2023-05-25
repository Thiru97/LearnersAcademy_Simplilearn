package com.learneracademy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.learneracademy.entity.MyClass;
import com.learneracademy.util.HibernateUtil;

public class ClassDAO {

	// add Class
	public boolean addClass(MyClass myclass) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.save(myclass);
			// commits transaction
			System.out.println("Class Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// get Class
	@SuppressWarnings("unchecked")
	public List<MyClass> getClassList() {
		Session session = null;
		List<MyClass> classList = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from MyClass";
			Query query = session.createQuery(hql);

			classList = query.getResultList();

			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	// delete Class
	public boolean deleteClass(int id) {
		Session session = null;
		MyClass myClass = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			myClass = session.load(MyClass.class, id);
			System.out.println(myClass);

			if (myClass != null) {
				session.delete(myClass);
				System.out.println("Class deleted Successfully");
			} else {
				return false;
			}
			// commits transaction
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// update Class
	public boolean updateClass(MyClass myclass) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.update(myclass);
			// commits transaction
			System.out.println("Class updated successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// getClass by class name
	public MyClass getClass(String className) {
		MyClass myClass = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from MyClass myClass where myClass.className = :className";
			Query query = session.createQuery(hql);
			query.setParameter("className", className);

			myClass = (MyClass) query.getSingleResult();

			// commits transaction
			System.out.println("Class found ");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClass;
	}

	// getClass by id

	// update Class
	public MyClass getClass(int id) {
		Session session = null;
		MyClass myClass = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			myClass = session.get(MyClass.class, id);
			// commits transaction
			System.out.println("Class updated successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClass;
	}

}
