package com.horrorcore;

import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private int age;
    private int grade;
    private String subject;
    private int score;

    public Student() {}

    public Student(int id, String name, int age, int grade, String subject, int score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.subject = subject;
        this.score = score;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int age() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int grade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String subject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int score() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Student student)) return false;
        return id == student.id && age == student.age && grade == student.grade && score == student.score && Objects.equals(name, student.name) && Objects.equals(subject, student.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, grade, subject, score);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", grade=" + grade +
                ", subject='" + subject + '\'' +
                ", score=" + score +
                '}';
    }
}
