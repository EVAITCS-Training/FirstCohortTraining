package com.horrorcore;

public interface IAnimal {
    final long serialVersionUID = 1L;
    static long serialVersionUIID = 1L;
//String name;

    void eat();

    void sleep();

    void move();

    void makeSound();

    void reproduce();

    void grow();

    void die();

    void defend();

    void hunt();

    void communicate();

    void adapt();

    default void showInfo() {
        System.out.println("Hello from interface");
    }

    static void staticMethod() {
        System.out.println("Hello from static method");
    }
}
