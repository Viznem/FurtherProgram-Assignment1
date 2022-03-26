package com.example.assignment1.Courses;

import dnl.utils.text.table.TextTable;

import java.util.Arrays;
import java.util.List;

public class ListOfCourse {
    private static final List<Course> COURSES = Arrays.asList(
            new Course(1, "AI", "3"),
            new Course(2, "Data", "4"),
            new Course(3, "Web", "5")

    );

    public List<Course> getAll() {
        return COURSES;
    }

    public Course getOne(int courseId) {
        return COURSES.stream()
                .filter(course -> course.getId() == courseId)
                .parallel()
                .findAny()
                .orElse(null);
    }

    public void print(List<Course> courses) {
        System.out.println("     <<<>>>  List of courses <<<>>>    ");
        String[] headers = new String[] {"Course Id", "Course Name", "Credit Number"};
        Object[][] data = new Object[courses.size()][headers.length];
        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            data[i][0] = course.getId();
            data[i][1] = course.getName();
            data[i][2] = course.getCredits();
        }
        TextTable tt = new TextTable(headers, data);
        tt.printTable();
    }
}
