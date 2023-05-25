package com.learneracademy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "class")
public class MyClass {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "classname")
	private String className;

	@ManyToMany(mappedBy = "myClass", cascade = CascadeType.DETACH)
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Subject> subject = new ArrayList<Subject>();

	@ManyToMany(mappedBy = "myClass", fetch = FetchType.EAGER)
	List<Teacher> teacher = new ArrayList<Teacher>();

	@OneToMany(mappedBy = "myClass", fetch = FetchType.LAZY)
	List<Student> student = new ArrayList<>();

	public MyClass() {

	}

	public MyClass(int id, String className, List<Subject> subject, List<Teacher> teacher, List<Student> student) {
		super();
		this.id = id;
		this.className = className;
		this.subject = subject;
		this.teacher = teacher;
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	public List<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

	@Override
	public int hashCode() {
		return Objects.hash(className, id, student, subject, teacher);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyClass other = (MyClass) obj;
		return Objects.equals(className, other.className) && id == other.id && Objects.equals(student, other.student)
				&& Objects.equals(subject, other.subject) && Objects.equals(teacher, other.teacher);
	}

}
