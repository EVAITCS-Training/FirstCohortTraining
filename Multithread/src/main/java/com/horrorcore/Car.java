package com.horrorcore;

import java.util.Objects;

public class Car extends Thread {
    private String name;
    private int speed;
    private int acceleration;
    private boolean isOn;

    public Car() {
        this.name = "Car";
        this.speed = 0;
        this.acceleration = 0;
        this.isOn = false;
    }

    public Car(String name, int speed, int acceleration, boolean isOn) {
        this.name = name;
        this.speed = speed;
        this.acceleration = acceleration;
        this.isOn = isOn;
    }

    public String name() {
        return name;
    }

    public int speed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int acceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void run() {
        while (isOn) {
            try {
                Thread.sleep(1000);
                speed += acceleration;
                System.out.println(name + " is running at " + speed + " km/h");
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted.");
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car car)) return false;
        return speed == car.speed && acceleration == car.acceleration && isOn() == car.isOn() && Objects.equals(getName(), car.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), speed, acceleration, isOn());
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", acceleration=" + acceleration +
                ", isOn=" + isOn +
                '}';
    }
}
