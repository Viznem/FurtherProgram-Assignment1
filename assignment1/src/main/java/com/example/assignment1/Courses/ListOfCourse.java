package com.example.assignment1.Courses;

import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListOfCourse {
    private static final List<Course> CourseList = new ArrayList<>();

    public List<Course> getAll() {
        return CourseList;
    }

    public void addCourse(String id, String name, String credits) {
        CourseList.add(new Course(id,name,credits));
    }

    public Course getOne(String courseId) {
        return CourseList.stream()
                .filter(course -> Objects.equals(course.getId(), courseId))
                .parallel()
                .findAny()
                .orElse(null);
    }

    public void print(List<Course> courses) {
        System.out.println("---------List of courses---------");
        String[] titles = new String[] {"Course Id", "Course Name", "Credit Number"};
        Object[][] body = new Object[courses.size()][titles.length];
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            body[i][0] = course.getId();
            body[i][1] = course.getName();
            body[i][2] = course.getCredits();
        }
        TextTable tt = new TextTable(titles, body);
        tt.printTable();
    }
}
