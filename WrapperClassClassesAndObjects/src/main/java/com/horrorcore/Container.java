package com.horrorcore;

import java.util.List;

public class Container<T> {
    private List<T> list;

    public Container(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void add(T item) {
        list.add(item);
    }

    public void remove(T item) {
        list.remove(item);
    }

    public T get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    public boolean contains(T item) {
        return list.contains(item);
    }
}
