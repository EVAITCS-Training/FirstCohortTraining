package com.horrorcore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class People {
    private String name;
    private int age;
    private double salary;
    private boolean isMarried;
    private boolean isEmployed;
    private List<String> secrets;

    public People() {} // Default constructor

    public People(String name, int age, double salary, boolean isMarried, boolean isEmployed) { // Constructor with all parameters
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.isMarried = isMarried;
        this.isEmployed = isEmployed;
        this.secrets = new ArrayList<>();
    }

    public People(String name, int age) { // Constructor with name and age only
        this.name = name;
        this.age = age;
        this.salary = 0.0;
        this.isMarried = false;
        this.isEmployed = false;
        this.secrets = new ArrayList<>();
    }

    public People(People people) { // Copy constructor
        // Copying the values from the passed object
        // to the new object
        // This is a Deep Copy
        this.name = people.name;
        this.age = people.age;
        this.salary = people.salary;
        this.isMarried = people.isMarried;
        this.isEmployed = people.isEmployed;
        this.secrets = new ArrayList<>(people.secrets); // Deep copy of the list
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
    * This is to inform if the person is married or not.
    *
    * @return true if the person is married, false otherwise
    *
    * */
    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public boolean isEmployed() {
        return isEmployed;
    }

    public void setEmployed(boolean employed) {
        isEmployed = employed;
    }

    public void setSecrets(List<String> secrets) {
        this.secrets = secrets;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof People people)) return false;
        return getAge() == people.getAge() && Double.compare(getSalary(), people.getSalary()) == 0 && isMarried() == people.isMarried() && isEmployed() == people.isEmployed() && Objects.equals(getName(), people.getName()) && Objects.equals(secrets, people.secrets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getSalary(), isMarried(), isEmployed(), secrets);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("People{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", salary=").append(salary);
        sb.append(", isMarried=").append(isMarried);
        sb.append(", isEmployed=").append(isEmployed);
        sb.append('}');
        return sb.toString();
    }
}
