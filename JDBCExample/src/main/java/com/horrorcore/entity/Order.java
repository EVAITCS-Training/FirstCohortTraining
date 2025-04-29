package com.horrorcore.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {
    private int orderNumber;
    private int userId;
    private LocalDateTime orderAt;
    private double total;
    private String payment;
    private List<MenuItem> menuItems;

    public Order() {
    }

    public Order(int userId, LocalDateTime orderAt, double total, String payment, List<MenuItem> menuItems) {
        this.userId = userId;
        this.orderAt = orderAt;
        this.total = total;
        this.payment = payment;
        this.menuItems = menuItems;
    }

    public Order(int orderNumber, int userId, LocalDateTime orderAt, double total, String payment, List<MenuItem> menuItems) {
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.orderAt = orderAt;
        this.total = total;
        this.payment = payment;
        this.menuItems = menuItems;
    }

    public int orderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int userId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime orderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.orderAt = orderAt;
    }

    public double total() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String payment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<MenuItem> menuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return orderNumber == order.orderNumber && userId == order.userId && Double.compare(total, order.total) == 0 && Objects.equals(orderAt, order.orderAt) && Objects.equals(payment, order.payment) && Objects.equals(menuItems, order.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, userId, orderAt, total, payment, menuItems);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNumber=" + orderNumber +
                ", userId=" + userId +
                ", orderAt=" + orderAt +
                ", total=" + total +
                ", payment='" + payment + '\'' +
                ", menuItems=" + menuItems +
                '}';
    }
}
