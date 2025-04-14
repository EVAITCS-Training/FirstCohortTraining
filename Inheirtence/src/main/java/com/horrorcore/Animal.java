package com.horrorcore;

import java.util.Objects;

public class Animal extends Object {
    private boolean isAlive;
    private String movement;
    private int eyes;
    private String color;
    private String name;
    protected String type;

    public Animal() {
        this.isAlive = true;
        this.movement = "walk";
        this.eyes = 2;
        this.color = "brown";
        this.name = "animal";
    }

    public Animal(boolean isAlive, String movement, int eyes, String color, String name) {
        this.isAlive = isAlive;
        this.movement = movement;
        this.eyes = eyes;
        this.color = color;
        this.name = name;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Animal setAlive(boolean alive) {
        isAlive = alive;
        return this;
    }

    public String getMovement() {
        return movement;
    }

    public Animal setMovement(String movement) {
        this.movement = movement;
        return this;
    }

    public int getEyes() {
        return eyes;
    }

    public Animal setEyes(int eyes) {
        this.eyes = eyes;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Animal setColor(String color) {
        this.color = color;
        return this;
    }

    public String getName() {
        return name;
    }

    public Animal setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Animal animal)) return false;
        return isAlive() == animal.isAlive() && getEyes() == animal.getEyes() && Objects.equals(getMovement(), animal.getMovement()) && Objects.equals(getColor(), animal.getColor()) && Objects.equals(getName(), animal.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isAlive(), getMovement(), getEyes(), getColor(), getName());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Animal{");
        sb.append("isAlive=").append(isAlive);
        sb.append(", movement='").append(movement).append('\'');
        sb.append(", eyes=").append(eyes);
        sb.append(", color='").append(color).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static void staticMethod() {
        System.out.println("Static method in Animal class");
    }
}
