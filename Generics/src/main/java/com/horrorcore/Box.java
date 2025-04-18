package com.horrorcore;

public class Box<T, K, V> {
    private T item;
    private K item2;
    private V item3;

    public Box(T item, K item2, V item3) {
        this.item = item;
        this.item2 = item2;
        this.item3 = item3;
    }

    public T item() {
        return item;
    }

    public K item2() {
        return item2;
    }

    public void setItem2(K item2) {
        this.item2 = item2;
    }

    public V item3() {
        return item3;
    }

    public void setItem3(V item3) {
        this.item3 = item3;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Box{");
        sb.append("item=").append(item);
        sb.append(", item2=").append(item2);
        sb.append(", item3=").append(item3);
        sb.append('}');
        return sb.toString();
    }
}
