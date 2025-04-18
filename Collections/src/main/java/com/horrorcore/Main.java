package com.horrorcore;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Arrays
        // Arrays does not have a regular class in Java
        // but it is a special type of object
        // Arrays are fixed in size
        // Arrays are zero indexed
        // Arrays can be of any type
//        float[] numbers = new float[10];
//        System.out.println(Arrays.toString(numbers));
//        // Arrays can be initialized with values
//        String[] names = {"Max", "Omer", "Akash", "Ashraf", "Kano"};
//
//        String[] names2 = new String[10];
//        for (int i = 0; i < names.length; i++) {
//            names2[i] = names[i];
//        }
//        names2[5]= "Matthew";
//        System.out.println(Arrays.toString(names));
//        System.out.println(Arrays.toString(names2));
//        // getting the length of an array
//        System.out.println("Length of names2: " + names2.length);
//
//        // can sort the array
//        Arrays.sort(names);
//        System.out.println("Sorted names2: " + Arrays.toString(names));
//        changeArray(names, 0, "Max");
//        changeArray(names, 3, "Akash");
//        System.out.println("Changed names2: " + Arrays.toString(names));
        // Lists
        // Lists are dynamic in size
        // Lists are zero indexed
        // Lists can be of any type minus primitives
        // Lists can be initialized with values
        // Lists can be sorted
        // Lists can be converted to arrays
        // List can start with a size
//        List<String> names = new ArrayList<>(5);
//        List<Integer> numbers = new ArrayList<>();
//        List<Double> decimals = List.of(5.5, 6.5, 7.5);
//        List<List<Boolean>> booleanGird = new ArrayList<>();
//        boolean[][] booleanGird2 = new boolean[5][5];
//
//        names.add("John");
//        names.add("Doe");
//        names.add("Jane");
//        names.add("Smith");
//        names.add("Max");
//        names.add("Omer");
//        names.add("Akash");
//        names.add("Ashraf");
//        names.add("Kano");
//        names.add("Matthew");
//
//        System.out.println("Names: " + names);
//
//        // can sort the list
//        names.sort(String::compareTo);
//
//        System.out.println("Sorted names: " + names);
//
//        // can remove an element from the list
//        names.remove("Max");
//        System.out.println("Removed Max: " + names);
//
//        // can remove an element from the list by index
//        names.remove(0);
//
//        System.out.println("Removed first element: " + names);
//        // can get an element from the list
//        String name = names.get(0);
//        System.out.println("First element: " + name);
//        // can get the index of an element from the list
//        int index = names.indexOf("Omer");
//        System.out.println("Index of Omer: " + index);
//
//        names.add(4, "Kano");
//        System.out.println("Added Kano at index 4: " + names);
        // Set
        // Set is a collection that does not allow duplicates
        // Set is a collection that does not allow null values
        // Set is a collection that does not allow order
        // Set is a collection that does not allow index
//        Set<String> names = new HashSet<>();
//        names.add("John");
//        names.add("Doe");
//        names.add("Jane");
//        names.add("Jack");
//        names.add("Max");
//        names.add("Omer");
//        names.add("Akash");
//        names.add("Ashraf");
//        names.add("Kano");
//        names.add("Matthew");
//        System.out.println("Names: " + names);
//        // Adding an element to a set
//        names.add("kano");
//        // This will not work because Set does not allow duplicates
//        System.out.println("Added Kano: " + names);
//
//        Set<String> names2 = new LinkedHashSet<>();
//        // LinkedHashSet maintains the order of insertion
//        Set<String> names3 = new TreeSet<>();
//        // TreeSet sorts the elements in natural order
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "John");
//        map.put("age", 25);
//        map.put("stats", new HashMap<String, Object>() {{
//            put("height", 5.8);
//            put("weight", 70);
//            put("eyeColor", "brown");
//            put("hairColor", "black");
//            put("isMarried", false);
//            put("isEmployed", true);
//            put("isStudent", false);
//            put("isAlive", true);
//            put("isSick", false);
//            put("isHappy", true);
//            put("isSad", false);
//        }});
//        map.put("friends", new ArrayList<String>() {{
//            add("Max");
//            add("Omer");
//            add("Akash");
//            add("Ashraf");
//            add("Kano");
//            add("Matthew");
//        }});
//
//        System.out.println("Map: " + map);
//
//        map.forEach((key, value) -> {
//            if (value instanceof Map) {
//                System.out.println(key + ": " + ((Map<?, ?>) value).get("height"));
//            } else if (value instanceof List) {
//                System.out.println(key + ": " + ((List<?>) value).get(0));
//            } else {
//                System.out.println(key + ": " + value);
//            }
//        });

        //Person person = Person.builder().name("Matthew").gender("Male").build();
    }

    public static void changeArray(String[] names, int index, String name) {
        names[index] = name;
    }
}