package com.studentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

	private String name;
	private int age;
	private String studentId;
	private List<String> courses;

	public Student(String name, int age, String studentId) {
		super();
		if (validateName(name) && validateAge(age) && validateStudentId(studentId)) {
			this.name = name;
			this.age = age;
			this.studentId = studentId;
			courses = new ArrayList<String>();
		}
	}

	public void enrollCourse(String course) {
		if (!courses.contains(course)) {
			courses.add(course);
			System.out.println(this.name + " is enrolled to " + course + " successfully!!");
		} else {
			System.err.println("Student is already enrolled in the \"" + course + "\" course.");
		}

	}

	public void printStudentInfo() {
		System.out.println("--------------- Student Information ---------------");
		System.out.println("Student Name: " + this.name);
		System.out.println("Student Age: " + this.age);
		System.out.println("Student Id: " + this.studentId);
		System.out.println("Enrolled Courses: " + this.courses);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", studentId=" + studentId + ", courses=" + courses + "]";
	}

	public boolean validateName(String name) {
		String nameRegex = "[a-zA-Z\\s]+";
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(name);
		if (nameMatcher.matches()) {
			return true;
		} else {
			System.err.println("Invalid Name!! Student name can have only alphabets and spaces.");
			return false;
		}
	}

	public boolean validateAge(int age) {
		if (age >= 19 && age <= 35) {
			return true;
		} else {
			System.err.println("Invalid Age!! Student age needs to be between 19 and 35.");
			return false;
		}
	}

	public boolean validateStudentId(String studentId) {
		String studentRegex = "^S-[0-9]+$";
		Pattern studenPattern = Pattern.compile(studentRegex);
		Matcher studenMatcher = studenPattern.matcher(studentId);
		if (studenMatcher.matches()) {
			return true;
		} else {
			System.err.println("Invalid Student Id!! should be in S-Integer format e.g. S-11");
			return false;
		}
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getStudentId() {
		return studentId;
	}

	public List<String> getCourses() {
		return courses;
	}

}
