package com.learneracademy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.learneracademy.entity.Subject;
import com.learneracademy.util.HibernateUtil;

public class SubjectDAO {

	// add Subject
	public boolean addSubject(Subject subject) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.save(subject);
			// commits transaction
			System.out.println("Subject Name added successfully");
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
	public List<Subject> getSubjectList() {
		Session session = null;
		List<Subject> subjectList = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from Subject";
			Query query = session.createQuery(hql);

			subjectList = query.getResultList();

			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subjectList;
	}

	// delete Class
	public boolean deleteSubject(int id) {
		Session session = null;
		Subject subject = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			subject = session.load(Subject.class, id);
			System.out.println(subject);

			if (subject != null) {
				session.delete(subject);
				System.out.println("Subject deleted Successfully");
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
	// getSubject by Name

	public Subject getSubject(String subjectName) {
		Subject subject = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from Subject subject where subject.subjectName	 = :subjectName";
			Query query = session.createQuery(hql);
			query.setParameter("subjectName", subjectName);

			subject = (Subject) query.getSingleResult();

			// commits transaction
			System.out.println("Class Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subject;
	}

	public boolean updateSubject(Subject subject) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.update(subject);
			// commits transaction
			System.out.println("Subject Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// get subject by id
	public Subject getSubject(int id) {
		Session session = null;
		Subject subject = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			subject = session.get(Subject.class, id);
			// commits transaction
			System.out.println("Subject Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subject;
	}
}
