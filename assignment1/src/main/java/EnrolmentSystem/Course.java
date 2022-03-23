package EnrolmentSystem;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private String courseID;
    private ArrayList<Student> studentList;
    public Course() {
        this.courseName = "default";
        this.courseID = "c001";
        this.studentList = new ArrayList<Student>();
    }
    public Course(String courseName, String courseID) {
        this.courseName = courseName;
        this.courseID = courseID;
        this.studentList = new ArrayList<Student>();
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCourseID() {
        return courseID;
    }
    public ArrayList<Student> getStudentList() {
        return studentList;
    }
    public boolean enroll (Student student) {
        if (studentList.contains(student)){
            return false;
        }
        studentList.add(student);
        student.getCourseList().add(this);
        return true;
    }
    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseID='" + courseID + '\'' +
                '}';
    }
}
