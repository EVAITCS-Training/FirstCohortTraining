package com.horrorcore.javaBurger.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * Represents a menu item in the Java Burger application.
 * Each menu item has a unique identifier, a name, and a price.
 */
@Entity
@Table(name = "jb_menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;
    @Column(nullable = false)
    @NotNull(message = "Price cannot be null")
    private double price;

    public MenuItem() {}

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public UUID id() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double price() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
