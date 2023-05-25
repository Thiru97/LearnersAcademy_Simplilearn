package com.learneracademy.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.learneracademy.entity.MyClass;
import com.learneracademy.entity.Student;
import com.learneracademy.util.HibernateUtil;

public class StudentDAO {

//add Student
	public boolean addStudent(Student student) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.save(student);
			// commits transaction
			System.out.println("Student Name added successfully");
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
	public List<Student> getStudentList() {
		Session session = null;
		List<Student> studentList = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from Student";
			Query query = session.createQuery(hql);

			studentList = query.getResultList();

			session.getTransaction().commit();
			// closing the session
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	// delete Class
	public boolean deleteStudent(int id) {
		Session session = null;
		Student student = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			student = session.load(Student.class, id);
			System.out.println(student);

			if (student != null) {
				session.delete(student);
				System.out.println("Student deleted Successfully");
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

	public Student getStudent(String firstName) {
		Session session = null;
		Student student = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from Student where firstName = :firstName";
			Query query = session.createQuery(hql);
			query.setParameter("firstName", firstName);

			student = (Student) query.getSingleResult();
			// commits transaction
			System.out.println("Student Name Found successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	// update Student
	public boolean updateStudent(Student student) {
		Session session = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();
			// Saving object
			session.update(student);
			// commits transaction
			System.out.println("Student updated	successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	// getStudent of Class
	@SuppressWarnings("unchecked")
	public List<Student> getStudent(MyClass myClass) {
		Session session = null;
		List<Student> studentList = null;
		try {

			session = HibernateUtil.getSession();

			session.beginTransaction();

			String hql = " from Student s where s.myClass = :myClass";
			Query query = session.createQuery(hql);
			query.setParameter("myClass", myClass);

			studentList = query.getResultList();
			// commits transaction
			System.out.println("Student Name Found successfully");
			session.getTransaction().commit();
			// closing the session
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}
}
