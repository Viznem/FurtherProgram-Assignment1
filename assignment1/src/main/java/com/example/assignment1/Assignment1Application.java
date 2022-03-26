package com.example.assignment1;

import com.example.assignment1.Courses.Course;
import com.example.assignment1.Courses.ListOfCourse;
import com.example.assignment1.Students.ListOfStudent;
import com.example.assignment1.Students.Student;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		Student StudentA = new Student(5, "Unknown", "10/10/2000");
		ListOfStudent students = new ListOfStudent();

		Course CourseA = new Course(4, "Further Program", "5");
		ListOfCourse courses = new ListOfCourse();

		System.out.println(StudentA);
		students.print(students.getAll());

		System.out.println(CourseA);
		courses.print(courses.getAll());

		//SpringApplication.run(Assignment1Application.class, args);
	}

}
