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
import java.util.Scanner;

@SpringBootApplication
public class Assignment1Application {
	static Scanner scanner = new Scanner(System.in);
	static ListOfStudent students = new ListOfStudent();
	static ListOfCourse courses = new ListOfCourse();
	static String path = "";
	static String line = "";

	public static void main(String[] args) {
		importCSVFile();
	}

	public static void readDefaultCSV(){
		path = "/assignment1/assignment1/src/main/resources/default.csv";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				students.addData(values[0].replace(" ",""),values[1].replace(" ",""),values[2].replace(" ",""));
				courses.addCourse(values[3].replace(" ",""),values[4].replace(" ",""),values[5].replace(" ",""));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		students.print(students.getAll());
		courses.print(courses.getAll());

		mainMenu();
	}

	public static void readUserCSVFile(){
		//the file must be in the same directory.
		System.out.print("\tEnter PATH to your CSV file (ex: /assignment1/assignment1/src/main/resources/YourFile.csv): ");
		path = scanner.nextLine();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				students.addData(values[0].replace(" ",""),values[1].replace(" ",""),values[2].replace(" ",""));
				courses.addCourse(values[3].replace(" ",""),values[4].replace(" ",""),values[5].replace(" ",""));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		students.print(students.getAll());
		courses.print(courses.getAll());

		mainMenu();
	}


	public static void  importCSVFile(){
		ControlPanel ChooseFile = new ControlPanel("Choose csv file to import");
		ChooseFile.addOption("Use default CSV file",Assignment1Application::readDefaultCSV);
		ChooseFile.addOption("Import your own CSV file",Assignment1Application::readUserCSVFile);
		ChooseFile.start();
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
