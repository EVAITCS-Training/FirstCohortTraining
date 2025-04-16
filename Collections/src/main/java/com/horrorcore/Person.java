package com.horrorcore;

public class Person {
    private String name;
    private int age;
    private String gender;
    private double salary;

    public Person() {}

    public Person(String name, int age, String gender, double salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Person setAge(int age) {
        this.age = age;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public Person setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public double getSalary() {
        return salary;
    }

    public Person setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    public static class Builder {

        private Person person;

        public Builder() {
            this.person = new Person();
        }

        public Builder name(String name) {
            this.person.setName(name);
            return this;
        }

        public Builder age(int age) {
            this.person.setAge(age);
            return this;
        }

        public Builder gender(String gender) {
            this.person.setGender(gender);
            return this;
        }

        public Builder salary(double salary) {
            this.person.setSalary(salary);
            return this;
        }

        public Person build() {
            return this.person;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
