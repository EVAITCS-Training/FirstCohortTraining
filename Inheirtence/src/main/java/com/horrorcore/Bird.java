package com.horrorcore;

import java.util.Objects;

public class Bird extends Animal {
    private String species;
    private String sound;

    public Bird() {
        super();
        this.species = "sparrow";
        this.sound = "chirp";
        this.type = "bird";
    }

    public Bird(boolean isAlive, String movement, int eyes, String color, String name, String species, String sound) {
        super(isAlive, movement, eyes, color, name);
        this.species = species;
        this.sound = sound;
    }

    public String getSpecies() {
        return species;
    }

    public Bird setSpecies(String species) {
        this.species = species;
        return this;
    }

    public String getSound() {
        return sound;
    }

    public Bird setSound(String sound) {
        this.sound = sound;
        return this;
    }

    public void fly() {
        System.out.println("The bird is flying.");
    }

    public void layEggs() {
        System.out.println("The bird is laying eggs.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bird)) return false;
        if (!super.equals(o)) return false;
        Bird bird = (Bird) o;
        return Objects.equals(species, bird.species) && Objects.equals(sound, bird.sound);
    }

    public boolean equalsForBirds(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bird)) return false;
        if (!super.equals(o)) return false;
        Bird bird = (Bird) o;
        return Objects.equals(species, bird.species);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), species, sound);
    }

    public static void staticMethod() {
        System.out.println("Static method in Bird class");
    }
}
