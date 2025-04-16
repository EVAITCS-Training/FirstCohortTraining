package com.horrorcore.entities.car;

import java.util.List;

public class Car {
    // This car object has a releationship with the following objects:
    // Engine, Transmission, Brake, GasTank, Tire
    // its a Has a relationship
    private Engine engine;
    private Transmission transmission;
    private Brake brake;
    private GasTank gasTank;
    private String color;
    private List<Tire> tires;
}
