package com.horrorcore;

import java.io.Serializable;

public class Anombiea extends Animal implements Cloneable, Serializable{

    public Anombiea() {
        super();
    }

    public Anombiea(boolean isAlive, String movement, int eyes, String color, String name) {
        super(isAlive, movement, eyes, color, name);
    }
}
