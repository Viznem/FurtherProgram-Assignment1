package com.example.assignment1;

import com.example.assignment1.ControlPanel.CRUDEnrollment;
import com.example.assignment1.Courses.Course;
import com.example.assignment1.Courses.ListOfCourse;
import com.example.assignment1.EnrolmentSystem.StudentEnrolment;
import com.example.assignment1.EnrolmentSystem.StudentEnrolmentManager;
import com.example.assignment1.Students.ListOfStudent;
import com.example.assignment1.Students.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


class Assignment1ApplicationTests {
	private static ListOfStudent StudentsList;
	private static ListOfCourse CoursesList;
	private static StudentEnrolmentManager StudentEnrolmentManager;
	private static String path = "/NguyenTruongThinh_S3777196/assignment1/src/main/resources/default1.csv";
	private static String line = "";

	// ALL this test work well when you run it SEPARATELY.
	// The setUp method keep adding data to the Enrolment List, make some test inaccurately.
	@BeforeEach
	public void setUp() {
		StudentEnrolmentManager = new CRUDEnrollment();
		StudentsList = new ListOfStudent();
		CoursesList = new ListOfCourse();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			while((line = br.readLine()) != null){
				String[] values = line.split(",");
				StudentsList.addData(values[0].replace(" ",""),values[1].replace(" ",""),values[2].replace(" ",""));
				CoursesList.addCourse(values[3].replace(" ",""),values[4].replace(" ",""),values[5].replace(" ",""));
				StudentEnrolmentManager.add(new StudentEnrolment(StudentsList.getOne(values[0]),
						CoursesList.getOne(values[3]),
						values[6]));
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void add() {
		Student student = StudentsList.getOne("S101312");
		Course course = CoursesList.getOne("COSC3321");
		String semester = "2021A";
		StudentEnrolmentManager.add(new StudentEnrolment(student,course,semester));
		//expect true
		Assertions.assertEquals(16, StudentEnrolmentManager.getAll().size());
	}

	@Test
	void Delete(){
		List<StudentEnrolment> enrolmentList= StudentEnrolmentManager.getAll();
		String enrolmentID = enrolmentList.get(0).getId();
		StudentEnrolmentManager.delete(enrolmentID);
		Assertions.assertEquals(14,StudentEnrolmentManager.getAll().size());
	}

	@Test
	void getOne(){
		List<StudentEnrolment> enrolmentList= StudentEnrolmentManager.getAll();
		String enrolmentID = enrolmentList.get(0).getId();
		StudentEnrolment student = StudentEnrolmentManager.getOne(enrolmentID);
		Assertions.assertEquals("AlexMike",student.getStudentName());
	}

	@Test
	void getAll(){
		Assertions.assertEquals(15, StudentEnrolmentManager.getAll().size());
	}

	@Test
	void GetEnrollmentDataForOneStudent(){
		String studentID = "S101312";
		String courseID = "COSC4030";
		String semester = "2020C";
		String StudentName = StudentEnrolmentManager.getEnrollmentDataForOneStudent(studentID, courseID, semester).getStudentName();
		Assertions.assertEquals("AlexMike", StudentName);
	}
}
