package com.example.assignment1;

import com.example.assignment1.ControlPanel.CRUDEnrollment;
import com.example.assignment1.ControlPanel.ControlPanel;
import com.example.assignment1.ControlPanel.PrintControl;
import com.example.assignment1.Courses.ListOfCourse;
import com.example.assignment1.Students.ListOfStudent;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class Assignment1Application {

	public static void main(String[] args) {

		ListOfStudent students = new ListOfStudent();
		ListOfCourse courses = new ListOfCourse();


		String path = "/assignment1/assignment1/src/main/resources/default.csv";
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				students.addData(values[0],values[1],values[2]);
				courses.addCourse(values[3],values[4],values[5]);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		students.print(students.getAll());
		courses.print(courses.getAll());

		mainMenu();
	}
	public static void mainMenu() {

		ControlPanel MainMenu = new ControlPanel("Main Menu");
		MainMenu.addOption("Enroll Menu", CRUDEnrollment::EnrollMenu);
		MainMenu.addOption("Print Data to CSV files", PrintControl::PrintMenu);
		MainMenu.addOption("List of student", CRUDEnrollment::DisplayAllStudents);
		MainMenu.addOption("List of course", CRUDEnrollment::DisplayAllCourses);
		MainMenu.addOption("Exit",()-> System.out.println("Exiting"));
		MainMenu.start();
	}
}
