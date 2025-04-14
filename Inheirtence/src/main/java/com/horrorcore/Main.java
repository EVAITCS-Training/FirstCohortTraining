package com.horrorcore;

import com.horrorcore.man.Man;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//       Bird bird = new Bird(true, "flying", 2,"Brown", "Thunderbird","sparrow", "chirp");
//       Animal animal = bird;
//       Bird bird1 = (Bird) animal;
//        System.out.println(bird1);
//        System.out.println(bird);
//        System.out.println(bird1.getSound());
//
//        System.out.println("Animal is alive: " + animal.isAlive());
//        System.out.println("Animal movement: " + animal.getMovement());
//
//        bird.fly();
//
//        Animal animal1 = new Animal();
//        if (animal1 instanceof Mammal mammal) {
//            mammal = (Mammal) animal1;
//            System.out.println("Mammal is alive: " + mammal.isAlive());
//        }
//
//        if(animal1.getClass() == Mammal.class) {
//            System.out.println("Animal is a Mammal");
//        } else {
//            System.out.println("Animal is not a Mammal");
//        }


//        Mammal mammal = (Mammal) animal;
//
//        System.out.println("Mammal is alive: " + mammal.isAlive());
//        System.out.println("Mammal movement: " + mammal.getMovement());

//        Animal animal1 = new Animal();
//
//        Mammal mammal = (Mammal) animal1;
//        System.out.println("Mammal is alive: " + mammal.isAlive());
//        System.out.println("Mammal movement: " + mammal.getMovement());
//        Calculator.add(6,4);
//        Calculator.add(2,2,8);
    List<Animal> animals = new ArrayList<>();
        Man man = new Man("Name", 18);

        man.age = 20;
    }
}