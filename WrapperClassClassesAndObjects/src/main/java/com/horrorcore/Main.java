package com.horrorcore;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Double a = 54.98;
//
//        System.out.println(a.shortValue());
//
//        String stringExample = "Billy Bob Joe";
//
//        System.out.println(stringExample.toLowerCase());
//
//        System.out.println();
//
//        System.out.println(stringExample.toUpperCase());
//
//        //int b = null; // This will cause a NullPointerException
//
//        Integer b = null; // This will not cause a NullPointerException
//
//        b = 10;
//
//        b = null;

        People johnDoe = new People("John Doe", 30, 50000.0, true, false); // this is one instance of the People class. A full object
        People janeDoe = new People("Jane Doe", 28, 60000.0, false, true); // another instance of the People class

        System.out.println(janeDoe == johnDoe); // false, because they are different objects

        System.out.println(janeDoe);
        System.out.println(johnDoe);

        janeDoe.isMarried();
    }
}