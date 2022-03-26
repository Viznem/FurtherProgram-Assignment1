package com.example.assignment1.EnrolmentSystem;

import java.util.List;

public interface StudentEnrolmentManager {
    void add(StudentEnrolment studentEnrolment);

    void update(int enrolmentId, StudentEnrolment studentEnrolment);

    void delete(int enrolmentId);

    void deleteByCourseId(int courseId);

    StudentEnrolment getOne(int enrolmentId);

    List<StudentEnrolment> getAll();
}
