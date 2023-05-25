package com.learneracademy.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "subject_name")
	private String subjectName;

	@Column(name = "subject_code")
	private String subjectCode;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "class_subject", joinColumns = @JoinColumn(name = "class_id"), inverseJoinColumns = @JoinColumn(name = "subject_id"))
	List<MyClass> myClass;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	Teacher teacher;

	public Subject() {

	}

	public Subject(int id, String subjectName, String subjectCode, List<MyClass> myClass, Teacher teacher) {
		super();
		this.id = id;
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
		this.myClass = myClass;
		this.teacher = teacher;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public List<MyClass> getMyClass() {
		return myClass;
	}

	public void setMyClass(List<MyClass> myClass) {
		this.myClass = myClass;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	@Override
	public String toString() {
		return "Subject [id=" + id + ", subjectName=" + subjectName + ", subjectCode=" + subjectCode + ", myClass="
				+ myClass + ", teacher=" + teacher + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, myClass, subjectCode, subjectName, teacher);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subject other = (Subject) obj;
		return id == other.id && Objects.equals(myClass, other.myClass)
				&& Objects.equals(subjectCode, other.subjectCode) && Objects.equals(subjectName, other.subjectName)
				&& Objects.equals(teacher, other.teacher);
	}

}
