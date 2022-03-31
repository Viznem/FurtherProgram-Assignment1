package com.example.assignment1.ControlPanel;

import com.example.assignment1.Assignment1Application;
import com.example.assignment1.Courses.Course;
import com.example.assignment1.Courses.ListOfCourse;
import com.example.assignment1.EnrolmentSystem.StudentEnrolment;
import com.example.assignment1.EnrolmentSystem.StudentEnrolmentManager;
import com.example.assignment1.Students.ListOfStudent;
import com.example.assignment1.Students.Student;
import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;


public class CRUDEnrollment implements StudentEnrolmentManager {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final ListOfStudent STUDENT_LIST = new ListOfStudent();
    public static final ListOfCourse COURSE_LIST = new ListOfCourse();
    private static List<StudentEnrolment> studentEnrolments = new ArrayList<>();
    private static final StudentEnrolmentManager ENROLMENT_MANAGER = new CRUDEnrollment();

    static String studentId;
    static String courseId;
    static String semester;

    public static void EnrollMenu() {
        ControlPanel menu = new ControlPanel("Control for Enrollment");
        menu.addOption("Create an Enrollment", CRUDEnrollment::CreateEnroll);
        menu.addOption("Update an Enrollment", CRUDEnrollment::UpdateEnroll);
        menu.addOption("Delete an Enrollment", CRUDEnrollment::DeleteEnroll);
        menu.addOption("Show all Enrollment", CRUDEnrollment::ShowAllEnroll);
        menu.addOption("Back main menu", Assignment1Application::mainMenu);
        menu.start();
    }

    private static void CreateEnroll() {
        System.out.print("\tEnter student id: ");
        studentId = SCANNER.nextLine();

        System.out.print("\tEnter course id: ");
        courseId = SCANNER.nextLine();

        System.out.print("\tEnter semester: ");
        semester = SCANNER.nextLine();

        if(STUDENT_LIST.getOne(studentId) != null && COURSE_LIST.getOne(courseId) != null){
            Student student = STUDENT_LIST.getOne(studentId);
            Course course = COURSE_LIST.getOne(courseId);
            ENROLMENT_MANAGER.add(new StudentEnrolment(student, course, semester));
            System.out.println("Create Enrollment Success");
            ShowAllEnroll();
            GoBack();
        }else{
            System.out.println("Student Id or Course Id did not exist!!!");
            GoBack();
        }

    }

    private static void UpdateEnroll() {
        System.out.print("\tEnter student id: ");
        studentId = SCANNER.nextLine();
        System.out.print("\tEnter semester: ");
        semester = SCANNER.nextLine();

        if(STUDENT_LIST.getOne(studentId) != null){
            ENROLMENT_MANAGER.getAllEnrolledCoursesForOneStudent(studentId, semester);
        }else{
            System.out.println("Student Id did not exist!!!");
            GoBack();
        }

        System.out.print("\tEnter old course ID you want to change: ");
        String oldCourseId = SCANNER.nextLine();

        if(COURSE_LIST.getOne(oldCourseId) != null){
            StudentEnrolment studentEnrolment = ENROLMENT_MANAGER.getEnrollmentDataForOneStudent(studentId, oldCourseId, semester);

            System.out.println("Course Available to Enroll");
            COURSE_LIST.print(COURSE_LIST.getAll());

            System.out.print("\tEnter new course id: ");
            String newCourseId = SCANNER.nextLine();

            if(COURSE_LIST.getOne(newCourseId) != null){
                String enrolmentId = studentEnrolment.getId();
                Course newCourse = COURSE_LIST.getOne(newCourseId);

                studentEnrolment.setCourse(newCourse);
                ENROLMENT_MANAGER.update(enrolmentId, studentEnrolment);

                System.out.println("Update Success");
                GoBack();
            }else{
                System.out.println("Course did not exist!!!");
                GoBack();
            }
        }else{
            System.out.println("Course did not exist!!!");
            GoBack();
        }

    }

    private static void DeleteEnroll() {
        System.out.print("\tEnter student id: ");
        studentId = SCANNER.nextLine();
        System.out.print("\tEnter semester: ");
        semester = SCANNER.nextLine();

        if(STUDENT_LIST.getOne(studentId) != null){
            ENROLMENT_MANAGER.getAllEnrolledCoursesForOneStudent(studentId, semester);

            System.out.print("\tEnter course id you want to drop: ");
            courseId = SCANNER.nextLine();

            if(COURSE_LIST.getOne(courseId) != null){
                ENROLMENT_MANAGER.deleteByCourseId(courseId);
                System.out.println("Delete Success");
                GoBack();
            }else{
                System.out.println("Course did not exist!!!");
                GoBack();
            }
        }else{
            System.out.println("Student did not exist!!!");
            GoBack();
        }

    }

    public static void DisplayAllStudents() {
        STUDENT_LIST.print(STUDENT_LIST.getAll());
        GoBack();
    }

    public static void DisplayAllCourses() {
        COURSE_LIST.print(COURSE_LIST.getAll());
        GoBack();
    }

    private static void GoBack() {
        ControlPanel menu = new ControlPanel("");
        menu.addOption("Back", Assignment1Application::mainMenu);
        menu.start();
    }


    @Override
    public void add(StudentEnrolment studentEnrolment) {
        studentEnrolments.add(studentEnrolment);
    }

    @Override
    public void update(String enrolmentId, StudentEnrolment studentEnrolment) {
        StudentEnrolment existedEnrolment = getOne(enrolmentId);
        if (existedEnrolment != null) {
            int index = studentEnrolments.indexOf(existedEnrolment);
            StudentEnrolment enrolment = new StudentEnrolment(studentEnrolment);
            studentEnrolments.set(index, enrolment);
        }
    }

    @Override
    public void delete(String enrolmentId) {
        StudentEnrolment studentEnrolment = getOne(enrolmentId);
        if (studentEnrolment != null) {
            studentEnrolments.remove(studentEnrolment);
        } else {
            System.out.println("Enrollment ID did not exist");
            GoBack();
        }
    }

    @Override
    public void deleteByCourseId(String courseId) {
        studentEnrolments = studentEnrolments.stream().filter(studentEnrolment -> !studentEnrolment.getCourse().getId().equalsIgnoreCase(courseId)).collect(Collectors.toList());
    }

    @Override
    public StudentEnrolment getOne(String enrolmentId) {
        return studentEnrolments.stream()
                .filter(enrolment -> Objects.equals(enrolment.getId(), enrolmentId))
                .parallel()
                .findAny()
                .orElse(null);
    }

    @Override
    public StudentEnrolment getEnrollmentDataForOneStudent(String studentId, String courseId, String semester) {
        return studentEnrolments.stream()
                .filter(studentEnrolment ->
                        Objects.equals(studentEnrolment.getStudent().getId(), studentId)
                                && Objects.equals(studentEnrolment.getCourse().getId(), courseId)
                                && studentEnrolment.getSemester().equalsIgnoreCase(semester))
                .parallel()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<StudentEnrolment> getAll() {
        return studentEnrolments;
    }

    private static void ShowAllEnroll(){
        System.out.println("     ****  List of all Enrollment ****    ");
        String[] headers = new String[] {"Enroll Id", "Student Name", "Course", "Semester"};
        Object[][] data = new Object[studentEnrolments.size()][headers.length];
        for (int i = 0; i < studentEnrolments.size(); i++) {
            StudentEnrolment studentEnrol = studentEnrolments.get(i);
            data[i][0] = studentEnrol.getId();
            data[i][1] = studentEnrol.getStudentName();
            data[i][2] = studentEnrol.getCourseName();
            data[i][3] = studentEnrol.getSemester();
        }
        TextTable tt = new TextTable(headers, data);
        tt.printTable();

        GoBack();
    }

    @Override
    public void getAllEnrolledCoursesForOneStudent(String studentId, String semester) {
        Student student = STUDENT_LIST.getOne(studentId);
        List<Course> courses = studentEnrolments.stream()
                .filter(studentEnrolment -> Objects.equals(studentEnrolment.getStudent().getId(), studentId) && studentEnrolment.getSemester().equalsIgnoreCase(semester))
                .map(StudentEnrolment::getCourse)
                .collect(Collectors.toList());
        System.out.println("              ***************           ");
        System.out.printf("Student Id: %s, Student Name: %s, Birthday: %s%n", student.getId(), student.getName(), student.getBirthdate());
        System.out.printf("Semester: %s%n", semester);
        System.out.printf("All Enrolled Courses of this student in Semester %s%n", semester);
        COURSE_LIST.print(courses);
    }

}