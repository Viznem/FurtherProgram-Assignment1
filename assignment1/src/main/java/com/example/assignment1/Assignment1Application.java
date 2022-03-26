package com.example.assignment1;

import com.example.assignment1.Students.ListOfStudent;
import com.example.assignment1.Students.Student;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {
		Student StudentA = new Student(5, "Unknown", "10/10/2000");
		ListOfStudent students = new ListOfStudent();
		System.out.println(StudentA);
		students.print(students.getAll());
		//SpringApplication.run(Assignment1Application.class, args);
	}

}
