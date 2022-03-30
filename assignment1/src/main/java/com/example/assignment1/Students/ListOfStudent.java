package com.example.assignment1.Students;

import dnl.utils.text.table.TextTable;

import java.util.Arrays;
import java.util.List;

public class ListOfStudent {

    private static final List<Student> StudentList = Arrays.asList(
            new Student(1, "Thinh Nguyen", "20/1/2000"),
            new Student(2, "Phat Nguyen", "20/1/2003"),
            new Student(3, "Hung Nguyen", "10/1/1995")
    );

    public List<Student> getAll() {
        return StudentList;
    }

    public Student getOne(int studentId) {
        return StudentList.stream()
                .filter(student -> student.getId() == studentId)
                .parallel()
                .findAny()
                .orElse(null);
    }

    public void print(List<Student> students) {
        System.out.println("-------List of students-------");
        String[] titles = new String[] {"Student Id", "Student Name", "Birthday"};
        Object[][] body = new Object[students.size()][titles.length];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            body[i][0] = student.getId();
            body[i][1] = student.getName();
            body[i][2] = student.getBirthdate();
        }
        TextTable tt = new TextTable(titles, body);
        tt.printTable();
    }
}
