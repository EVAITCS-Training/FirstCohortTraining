package com.horrorcore;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<Student> students = new ArrayList<>();
        try (FileReader fr = new FileReader(Main.class.getClassLoader().getResource("student.csv").getFile())
             ;BufferedReader br = new BufferedReader(fr)) {
            while(br.readLine() != null) {
                Student student = new Student();
                String line = br.readLine();
                String[] data = line.split(",");
                student.setId(Integer.parseInt(data[0]));
                student.setName(data[1]);
                student.setAge(Integer.parseInt(data[2]));
                student.setGrade(Integer.parseInt(data[3]));
                student.setSubject(data[4]);
                student.setScore(Integer.parseInt(data[5]));
                students.add(student);
                student = null;
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }
}