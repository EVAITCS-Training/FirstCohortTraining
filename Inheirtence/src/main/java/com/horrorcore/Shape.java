package com.horrorcore;

public class Shape {
    private int length;
    private int width;
    private int height;

    public Shape() {
        this.length = 0;
        this.width = 0;
        this.height = 0;
    }

    public Shape(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public Shape setLength(int length) {
        this.length = length;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public Shape setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public Shape setHeight(int height) {
        this.height = height;
        return this;
    }

    public int calculateArea() {
        return length * width;
    }

    public int calculateVolume() {
        return length * width * height;
    }

    public boolean isClose() {
        return length < 10 && width < 10 && height < 10;
    }
}
