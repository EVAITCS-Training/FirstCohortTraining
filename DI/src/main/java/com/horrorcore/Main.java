package com.horrorcore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ManagementSystem managementSystem = new ManagementSystem(sc);
        managementSystem.start();

    }
}