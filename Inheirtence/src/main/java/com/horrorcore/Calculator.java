package com.horrorcore;

public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b;
    }

    public static int modulus(int a, int b) {
        return a % b;
    }

    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static double add(double a, double b, double c) {
        return a + b + c;
    }

    public static int subtract(int a, int b, int c) {
        return a - b - c;
    }

    public static int multiply(int a, int b, int c) {
        return a * b * c;
    }

    public static int divide(int a, int b, int c) {
        if (b == 0 || c == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return a / b / c;
    }

    public static int modulus(int a, int b, int c) {
        return a % b % c;
    }

}
