package com.horrorcore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        // Function interface
//        // Function<T, R> - takes one argument and returns a result
//        // BiFunction<T, U, R> - takes two arguments and returns a result
//        Function<Integer, Integer> sum = (x) -> x+x;
//        //BiFunction<Integer, Integer, Integer> sum = (x, y) -> x+y;
//        System.out.println(sum.apply(5));
//        System.out.println();
//        System.out.println(sum.apply(10));
//        System.out.println();
//        // Consumer interface
//        // Consumer<T> - takes one argument and returns no result
//        // BiConsumer<T, U> - takes two arguments and returns no result
//        Consumer<Integer> consumer = System.out::println;
//        consumer.accept(sum.apply(20));
//        System.out.println();
//        // Predicate interface
//        // Predicate<T> - takes one argument and returns a boolean
//        // BiPredicate<T, U> - takes two arguments and returns a boolean
//        Predicate<Integer> isEven = x -> x%2 == 0;
//        System.out.println(isEven.test(10));
//        System.out.println(isEven.test(11));
//        System.out.println();
//        // Supplier interface
//        // Supplier<T> - takes no arguments and returns a result
//        // IntSupplier - takes no arguments and returns an int
//        // LongSupplier - takes no arguments and returns a long
//        // DoubleSupplier - takes no arguments and returns a double
//        // BooleanSupplier - takes no arguments and returns a boolean
//        // To use a Supplier, you can use the get() method
//        Supplier<Integer> supplier = () -> new Random().nextInt(100);
//        System.out.println(supplier.get());
//        System.out.println();
//        System.out.println(supplier.get());
//        System.out.println();
//        System.out.println(supplier.get());
//        Runnable runnable = () -> System.out.println("Hello World");
//        Callable<String> callable = () -> {
//            System.out.println("Hello World");
//            return "Hello World";
//        };
//
//        TriFunction<Integer, Integer, Integer, Integer> triFunction = (x,y,z) -> x+y+z;
//
//        System.out.println(triFunction.apply(1,2,3));
//        System.out.println(triFunction.apply(6,2,5));
//        System.out.println(triFunction.apply(80,9,3));
//
//        Supplier<String> returnDigimonName = () -> {
//            List<String> digimonNames = List.of("Agumon", "Gabumon", "Patamon", "Gatomon", "Biyomon");
//            Random random = new Random();
//            return digimonNames.get(random.nextInt(digimonNames.size()));
//        };
//
//        Supplier<String> returnDigimonType = () -> {
//            List<String> digimonTypes = List.of("Vaccine", "Data", "Virus");
//            Random random = new Random();
//            return digimonTypes.get(random.nextInt(digimonTypes.size()));
//        };
//
//        Supplier<Integer> returnDigimonLevel = () -> {
//            Random random = new Random();
//            return random.nextInt(1, 100);
//        };
//
//        Digimon digimon = createDigimon(returnDigimonName, returnDigimonType, returnDigimonLevel);
//
//        System.out.println(digimon);

        List<String> names = new ArrayList<>();
        names.add("Dan");
        names.add("Max");
        names.add("Kano");
        names.add("Akash");
        names.add("Ashraf");
        names.add("Martin");

// Old-fashioned way of sorting
        List<String> sortedNamesOldFashioned = new ArrayList<>();

        for (String name : names) {
            boolean added = false;
            for (int i = 0; i < sortedNamesOldFashioned.size(); i++) {
                if (sortedNamesOldFashioned.get(i).compareTo(name) > 0) {
                    sortedNamesOldFashioned.add(i, name);
                    added = true;
                    break;
                }
            }
            if (!added) {
                sortedNamesOldFashioned.add(name);
            }
        }

// Using Java 8 Stream API
        List<String> sortedNamesJava8 = names.stream().sorted().collect(Collectors.toList());

        System.out.println("Unsorted names: " + names);
        System.out.println("Sorted names (Old-Fashioned): " + sortedNamesOldFashioned);
        System.out.println("Sorted names (Java 8): " + sortedNamesJava8);

        names.stream().filter(name -> name.startsWith("A")).forEach(System.out::println);
        names.stream().filter(name -> name.endsWith("ch")).forEach(System.out::println);
        names.stream().filter(name -> name.length() > 3).forEach(System.out::println);
        names.stream().map(String::toUpperCase).forEach(System.out::println);
        names.stream().mapToInt(x -> x.length()).forEach(System.out::println);
        names.stream().map(String::toUpperCase)
                .peek(System.out::println)
                .filter(name -> name.length() > 3)
                .forEach(System.out::println);

    }

    public static Digimon createDigimon(Supplier<String> nameSupplier, Supplier<String> typeSupplier, Supplier<Integer> levelSupplier) {
        return new Digimon(nameSupplier.get(), typeSupplier.get(), levelSupplier.get());
    }
}