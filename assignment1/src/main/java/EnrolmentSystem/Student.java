package EnrolmentSystem;

import java.util.ArrayList;

public class Student {
    private String studentName;
    private String studentID;
    private ArrayList<Course> courseList;
    public Student() {
        this.studentName = "Default";
        this.studentID = "s001";
        this.courseList = new ArrayList<Course>();
    }
    public Student(String studentName, String studentID) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.courseList = new ArrayList<Course>();
    }
    public String getStudentName() {
        return studentName;
    }
    public String getStudentID() {
        return studentID;
    }
    public ArrayList<Course> getCourseList() {
        return courseList;
    }
    @Override
    public String toString() {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }
}

