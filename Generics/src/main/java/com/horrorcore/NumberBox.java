package com.horrorcore;

public class NumberBox<T extends Number> {
    private T value;

    public NumberBox(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public double getDoubleValue() {
        return value.doubleValue();
    }
    public int getIntValue() {
        return value.intValue();
    }
    public long getLongValue() {
        return value.longValue();
    }
    public float getFloatValue() {
        return value.floatValue();
    }
    public short getShortValue() {
        return value.shortValue();
    }
    public byte getByteValue() {
        return value.byteValue();
    }
    public String toString() {
        return "NumberBox{" +
                "value=" + value +
                '}';
    }
}
