package com.horrorcore;

import java.util.Comparator;

public class Animal implements Comparable<Animal>, Comparator<Animal> {
    private String name;
    private int age;
    private String type;
    private String breed;
    private String color;

    public Animal(String name, int age, String type, String breed, String color) {
        this.name = name;
        this.age = age;
        this.type = type;
        this.breed = breed;
        this.color = color;
    }

    @Override
    public int compareTo(Animal o) {
        boolean isSameType = this.type.equals(o.type);
        boolean isSameBreed = this.breed.equals(o.breed);
        boolean isSameColor = this.color.equals(o.color);
        boolean isSameName = this.name.equals(o.name);
        boolean isSameAge = this.age == o.age;
        if (isSameType && isSameBreed && isSameColor && isSameName && isSameAge) {
            return 0;
        } else if (this.age > o.age) {
            return 1;
        } else if (this.age < o.age) {
            return -1;
        } else {
            return this.name.compareTo(o.name);
        }
    }

    @Override
    public int compare(Animal o1, Animal o2) {
        return 0;
    }
}
