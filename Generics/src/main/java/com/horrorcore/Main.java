package com.horrorcore;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Generics
        // Generics are a way to create classes, interfaces, and methods with a placeholder for the type of data they operate on.
        // This allows for code reusability and type safety.
        // For example, we can create a generic class that can hold any type of data:
        // Generic class


        Box<String, Integer, Boolean> box = new Box<>("Hello", 123, true);
        Box<Integer, String, String> box2 = new Box<>(123, "World", "!");

        List<Box> boxes = List.of(box, box2);

        for (Box box1 : boxes) {
            System.out.println("Box item: " + box1.item());
            System.out.println("Box item2: " + box1.item2());
            System.out.println("Box item3: " + box1.item3());
        }

        Helper<String> helper = new Helper<>();

        // Check the type of the item
        helper.checkType("Hello");
        NumberBox<Integer> numberBox = new NumberBox<>(123);
    }
}