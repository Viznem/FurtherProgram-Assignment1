package com.example.assignment1.EnrolmentSystem;


import com.example.assignment1.Courses.Course;
import com.example.assignment1.Students.Student;

import java.util.Objects;
import java.util.UUID;

public class StudentEnrolment {

    private String id;
    private Student student;
    private Course course;
    private String semester;

    public StudentEnrolment(String id, Student student, Course course, String semester) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public StudentEnrolment(Student student, Course course, String semester) {
        this.id = UUID.randomUUID().toString();
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    public StudentEnrolment(StudentEnrolment studentEnrolment) {
        this(studentEnrolment.getId(), studentEnrolment.getStudent(), studentEnrolment.getCourse(), studentEnrolment.getSemester());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }
    public String getStudentName() {
        return student.getName();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }
    public String getCourseName() {
        return course.getName();
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StudentEnrolment enrolment = (StudentEnrolment) obj;
        return Objects.equals(id, enrolment.id) && Objects.equals(student, enrolment.student) && Objects.equals(course, enrolment.course) && Objects.equals(semester, enrolment.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, course, semester);
    }

    @Override
    public String toString() {
        return "StudentEnrolment{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", semester='" + semester + '\'' +
                '}';
    }
}
