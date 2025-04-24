package com.horrorcore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car car = new Car();
        car.setAcceleration(5);
        while (true) {
            System.out.println("Enter command (start/stop/exit): ");
            String command = sc.nextLine();
            if (command.equalsIgnoreCase("start")) {
                car.setOn(true);
                car.start();
            } else if (command.equalsIgnoreCase("stop")) {
                car.setOn(false);
                System.out.println(car.name() + " has stopped.");
            } else if (command.equalsIgnoreCase("exit")) {
                car.setOn(false);
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

    }
}