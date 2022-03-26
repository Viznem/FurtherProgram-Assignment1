package com.example.assignment1.Courses;

import java.util.Objects;

public class Course {
    private int id;
    private String name;
    private String credits;


    public Course(int id, String name, String credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", credits='" + credits + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Course course = (Course) obj;
        return id == course.id && Objects.equals(name, course.name) && Objects.equals(credits, course.credits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, credits);
    }
}