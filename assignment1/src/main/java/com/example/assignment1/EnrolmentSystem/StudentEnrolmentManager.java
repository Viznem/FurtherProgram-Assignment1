package com.example.assignment1.EnrolmentSystem;

import java.util.List;

public interface StudentEnrolmentManager {
    void add(StudentEnrolment studentEnrolment);

    void update(String enrolmentId, StudentEnrolment studentEnrolment);

    void delete(String enrolmentId);

    void deleteByCourseId(String courseId);

    StudentEnrolment getOne(String enrolmentId);

    List<StudentEnrolment> getAll();

    StudentEnrolment getEnrollmentDataForOneStudent(String studentId, String oldCourseId, String semester);

    void getAllEnrolledCoursesForOneStudent(String studentId, String semester);
}
