package com.horrorcore.entity;

import java.util.Objects;

public class MenuItem {
    private long id;
    private String name;
    private double price;
    private int quantity;
    private char size;
    private boolean isMenu;

    public MenuItem() {
    }

    public MenuItem(String name, double price, int quantity, char size, boolean isMenu) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.isMenu = isMenu;
    }

    public MenuItem(long id, String name, double price, int quantity, char size, boolean isMenu) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
        this.isMenu = isMenu;
    }

    public long id() {
        return id;
    }

    public void setId(long id) {
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

    public int quantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public char size() {
        return size;
    }

    public void setSize(char size) {
        this.size = size;
    }

    public boolean isMenu() {
        return isMenu;
    }

    public void setMenu(boolean menu) {
        isMenu = menu;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MenuItem menuItem)) return false;
        return id == menuItem.id && Double.compare(price, menuItem.price) == 0 && quantity == menuItem.quantity && size == menuItem.size && isMenu() == menuItem.isMenu() && Objects.equals(name, menuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity, size, isMenu());
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", size=" + size +
                ", isMenu=" + isMenu +
                '}';
    }
}
