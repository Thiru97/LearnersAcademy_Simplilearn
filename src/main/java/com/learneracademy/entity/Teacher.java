package com.learneracademy.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "qualification")
	private String qualification;

	@OneToMany(mappedBy = "teacher")
	@LazyCollection(LazyCollectionOption.FALSE)
	List<Subject> subject;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "class_teacher", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	List<MyClass> myClass = new ArrayList<MyClass>();

	public Teacher() {

	}

	public Teacher(int id, String firstName, String lastName, String fullName, String qualification,
			List<Subject> subject, List<MyClass> myClass) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.fullName = fullName;
		this.qualification = qualification;
		this.subject = subject;
		this.myClass = myClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	public List<MyClass> getMyClass() {
		return myClass;
	}

	public void setMyClass(List<MyClass> myClass) {
		this.myClass = myClass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, fullName, id, lastName, myClass, qualification, subject);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(fullName, other.fullName) && id == other.id
				&& Objects.equals(lastName, other.lastName) && Objects.equals(myClass, other.myClass)
				&& Objects.equals(qualification, other.qualification) && Objects.equals(subject, other.subject);
	}

}
