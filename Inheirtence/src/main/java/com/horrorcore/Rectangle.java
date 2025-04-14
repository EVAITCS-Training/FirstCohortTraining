package com.horrorcore;

public class Rectangle extends Shape {


    @Override
    public int calculateArea() {
        return getLength() * getWidth() + 10;
    }
}
