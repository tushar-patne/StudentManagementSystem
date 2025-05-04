package com.studentapp;

import java.security.DigestOutputStream;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	private static List<Student> studentList = new ArrayList<>();
	private static Scanner scanner;

	public static void main(String[] args) {
		
		System.out.println("**************** Student Management System *****************");
		
		
		scanner = new Scanner(System.in);
		
		while(true) {
			System.out.println();
			System.out.println("*********************** Welcome ****************************");
			System.out.println("Select an option...");
			System.out.println("1. Register new student");
			System.out.println("2. Find student with student id");
			System.out.println("3. List all student information");
			System.out.println("4. List information in sorted order");
			System.out.println("5. Exit");
			
			int option = 0;
			try {
				option = scanner.nextInt();
			} catch (Exception e) {
				scanner.next(); // clear invalid input
				System.err.println("Invalid option! Enter number between 1 - 5");
				continue;
			}
			
			switch (option) {
			case 1:
				try {
					enrollStudent();
				} catch (Exception e) {
					System.out.println(e.getMessage());
					scanner.next(); // clear invalid input
					return;
				}
				break;
			case 2:
				findStudentById();
				break;
			case 3:
				getAllStudents(false);
				break;
			case 4:
				getAllStudents(true);
				break;
			case 5:
				System.out.println("Good Bye!!!");
				System.exit(0);
				break;
			default:
				System.err.println("Invalid option! Enter number between 1 to 5");
				break;
			}
		}
				
	}
	
	private static void getAllStudents(boolean applySort) {
		if(studentList.size() <=0 ) {
			System.err.println("No students!!");
		}else if (applySort) {
			sortByName(studentList);
			for(Student s: studentList) {
				s.printStudentInfo();
			}
		}else {
			for(Student s: studentList) {
				s.printStudentInfo();
			}
		}
		
	}

	private static void enrollStudent() {
		
		String studentName, studentId;
		int studentAge;
		System.out.print("Please enter student name: ");
		studentName = scanner.next();
		System.out.println("You have entered the name: " + studentName);
		
		
		try {
			System.out.print("Please enter student age: ");
			studentAge = scanner.nextInt();
			System.out.println("You have entered the age: " + studentAge);
		} catch (Exception e) {
			System.err.println("Age must be a number!");
		    scanner.next(); // clear invalid input
		    return;
		}
		
		System.out.print("Please enter student id: ");
		studentId = scanner.next();
		System.out.println("You have entered the student id: " + studentId);
		
		Student student = new Student(studentName, studentAge, studentId);
		studentList.add(student);
		
		while (true) {
			System.out.print("Please enter course name (enter /'done/' to exit): ");
			String course = scanner.next();
			if(course.equalsIgnoreCase("done")) {
				break;
			}
			student.enrollCourse(course);
			System.out.println(student.getName() + " enrolled in the following course: " + course);
		}
		
		for (Student s : studentList) {
			s.printStudentInfo();
		}
			
	}

	public static void sortByName(List<Student> list) {
		Collections.sort(list, (a, b) -> a.getName().compareTo(b.getName()));
	}
	
	public static void findStudentById() {
		System.out.println("Please enter student id: ");
		String studentId = scanner.next();
//		Student result = studentList.stream()
//				.filter(x -> x.getStudentId()
//				.equals(studentId))
//				.findFirst()
//				.orElseThrow(() -> new RuntimeException("Data not found!!"));
//		return result;
		boolean isValidStudentId = false;
		for(Student s: studentList) {
			if (s.getStudentId().equals(studentId)) {
				s.printStudentInfo();
				isValidStudentId = true;
				break;
			}
		}
		if(!isValidStudentId) {
			System.err.println("Student with ID " + studentId + " not found.");
		}
	}

}
