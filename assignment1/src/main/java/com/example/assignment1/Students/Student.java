package com.example.assignment1.Students;

import java.util.Objects;

public class Student {
    private String id;
    private String name;
    private String birthdate;

    public Student(String id, String name, String birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(birthdate, student.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthdate);
    }
}


