package com.learneracademy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.learneracademy.entity.Teacher;
import com.learneracademy.util.HibernateUtil;

public class TeacherDAO {

	// add Teacher
	public boolean addTeacher(Teacher teacher) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.save(teacher);
			// commits transaction
			System.out.println("Teacher Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// get teacherList
	@SuppressWarnings("unchecked")
	public List<Teacher> getTeacherList() {
		Session session = null;
		List<Teacher> teacherList = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from Teacher";
			Query query = session.createQuery(hql);

			teacherList = query.getResultList();

			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherList;
	}

	// update Teacher
	public boolean updateTeacher(Teacher teacher) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.saveOrUpdate(teacher);
			// commits transaction
			System.out.println("Teacher Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// delete teacher
	public boolean deleteTeacher(int id) {
		Session session = null;
		Teacher teacher = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			teacher = session.load(Teacher.class, id);
			System.out.println(teacher);

			if (teacher != null) {
				session.delete(teacher);
				System.out.println("Teacher deleted Successfully");
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

	// getTeacher by Name

	public Teacher getTeacher(String fullName) {
		Teacher teacher = null;
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from Teacher teacher where teacher.fullName	 = :fullName";
			Query query = session.createQuery(hql);
			query.setParameter("fullName", fullName);

			teacher = (Teacher) query.getSingleResult();

			// commits transaction
			System.out.println("Class Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}

	public Teacher getTeacher(int id) {
		Session session = null;
		Teacher teacher = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			teacher = session.get(Teacher.class, id);
			// commits transaction
			System.out.println("Teacher Name added successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}

}
