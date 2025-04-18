package com.horrorcore;

import java.util.List;

public class Helper<T> {

    public void checkType(T item) {
        if (item instanceof String) {
            System.out.println("The item is a String: " + item);
        } else if (item instanceof Integer) {
            System.out.println("The item is an Integer: " + item);
        } else if (item instanceof Boolean) {
            System.out.println("The item is a Boolean: " + item);
        } else if (item instanceof List<?>) {
            System.out.println("The item is a List: " + item);
        } else {
            System.out.println("The item is of type: " + item.getClass().getSimpleName());
        }
    }

}
