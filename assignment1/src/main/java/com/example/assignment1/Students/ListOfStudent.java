package com.example.assignment1.Students;

import dnl.utils.text.table.TextTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListOfStudent {

    private static final List<Student> StudentList = new ArrayList<>();


    public List<Student> getAll() {
        return StudentList;
    }

    public void addData(String id, String name, String birthdate) {
        StudentList.add(new Student(id,name,birthdate));
    }

    public Student getOne(String studentId) {
        return StudentList.stream()
                .filter(student -> Objects.equals(student.getId().toLowerCase(), studentId.toLowerCase()))
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
