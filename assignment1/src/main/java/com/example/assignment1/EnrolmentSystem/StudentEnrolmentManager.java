package com.example.assignment1.EnrolmentSystem;

import java.util.List;

public interface StudentEnrolmentManager {
    void add(StudentEnrolment studentEnrolment);

    void update(String enrolmentId, StudentEnrolment studentEnrolment);

    void delete(String enrolmentId);

    void deleteByCourseId(int courseId);

    StudentEnrolment getOne(String enrolmentId);

    List<StudentEnrolment> getAll();

    StudentEnrolment getEnrollmentDataForOneStudent(int studentId, int oldCourseId, String semester);

    void getAllEnrolledCoursesForOneStudent(int studentId, String semester);
}
