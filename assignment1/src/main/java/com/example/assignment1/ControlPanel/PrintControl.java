package com.example.assignment1.ControlPanel;

import com.example.assignment1.Courses.Course;
import com.example.assignment1.Courses.ListOfCourse;
import com.example.assignment1.EnrolmentSystem.StudentEnrolment;
import com.example.assignment1.Students.ListOfStudent;
import com.example.assignment1.Students.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;


public class PrintControl {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final ListOfStudent STUDENT_LIST = new ListOfStudent();
    public static final ListOfCourse COURSE_LIST = new ListOfCourse();
    public static CRUDEnrollment Enrollment_List = new CRUDEnrollment();

    static String studentId;
    static String courseId;
    static String semester;

    public static void PrintMenu(){
        ControlPanel printMenu = new ControlPanel("Print to CSV files");
        printMenu.addOption("Print All Enrolled Courses of 1 student in 1 semester", PrintControl::printAllEnrolledCoursesForOneStudentInOneSemester);
        printMenu.addOption("Print All Students Of 1 Course in 1 semester", PrintControl::printAllStudentsOfOneCourseInOneSemester);
        printMenu.start();
    }

    public static void printAllEnrolledCoursesForOneStudentInOneSemester() {
        System.out.print("\tEnter student id: ");
        studentId = SCANNER.nextLine();
        System.out.print("\tEnter semester: ");
        semester = SCANNER.nextLine();

        Student student = STUDENT_LIST.getOne(studentId);
        List<Course> courses = Enrollment_List.getAll().stream()
                .filter(studentEnrolment -> Objects.equals(studentEnrolment.getStudent().getId(), studentId) && studentEnrolment.getSemester().equalsIgnoreCase(semester))
                .map(StudentEnrolment::getCourse)
                .collect(Collectors.toList());
        System.out.println("              ***************           ");
        System.out.printf("Student Id: %s, Student Name: %s, Birthday: %s%n", student.getId(), student.getName(), student.getBirthdate());
        System.out.printf("Semester: %s%n", semester);
        COURSE_LIST.print(courses);

        if (!courses.isEmpty()) {
            System.out.println("Do you want to export to CSV file? Yes(Y) / No(N)");
            String choose = SCANNER.nextLine();
            if ("Y".equalsIgnoreCase(choose)) {
                try {
                    String fileName = "/assignment1/assignment1/src/main/resources/allEnrolledCourses.csv";
                    String[] headers = {"Course Id", "Course Name", "Credit Number"};
                    FileWriter out = new FileWriter(fileName);
                    try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
                        for (Course course : courses) {
                            printer.printRecord(course.getId(), course.getName(), course.getCredits());
                        }
                    }
                    System.out.printf("Exported report. File name is %s%n", fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void printAllStudentsOfOneCourseInOneSemester() {
        System.out.print("\tEnter Course id: ");
        courseId = SCANNER.nextLine();
        System.out.print("\tEnter semester: ");
        semester = SCANNER.nextLine();

        Course course = COURSE_LIST.getOne(courseId);

        List<Student> students = Enrollment_List.getAll().stream()
                .filter(studentEnrolment -> Objects.equals(studentEnrolment.getCourse().getId(), courseId) && studentEnrolment.getSemester().equalsIgnoreCase(semester))
                .map(StudentEnrolment::getStudent)
                .collect(Collectors.toList());
        System.out.println("============================================================");
        System.out.printf("Course Id: %s, Course Name: %s, Credit Number: %s%n", course.getId(), course.getName(), course.getCredits());
        System.out.printf("Semester: %s%n", semester);
        STUDENT_LIST.print(students);

        if (!students.isEmpty()) {
            System.out.println("Do you want to export to CSV file? Yes(Y) / No(N)");
            String choose = SCANNER.nextLine();
            if ("Y".equalsIgnoreCase(choose)) {
                try {
                    String fileName = "/assignment1/assignment1/src/main/resources/allStudentsInCourse.csv";
                    String[] headers = {"StudentId Id", "Student Name", "Birthday"};
                    FileWriter out = new FileWriter(fileName);
                    try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader(headers))) {
                        for (Student student : students) {
                            printer.printRecord(student.getId(), student.getName(), student.getBirthdate());
                        }
                    }
                    System.out.printf("Exported report. File name is %s%n", fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

