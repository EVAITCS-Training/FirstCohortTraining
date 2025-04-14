package com.horrorcore;

import com.horrorcore.man.Man;

public class Child extends Man {
    public Child(String name, int age) {
        super(name, age);
    }

    @Override
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }
}
