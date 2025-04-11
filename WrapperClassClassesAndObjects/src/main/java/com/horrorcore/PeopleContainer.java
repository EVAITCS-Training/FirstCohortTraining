package com.horrorcore;

import java.util.List;

public class PeopleContainer extends Container<People> {


    public PeopleContainer(List<People> list) {
        super(list);
    }

    public void addPerson(People person) {
        add(person);
    }

    public List<People> getPersons() {
        return getList();
    }

    public void removePerson(People person) {
        remove(person);
    }

    public People getPerson(int index) {
        return get(index);
    }

    public int getPersonCount() {
        return size();
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public void clearPersons() {
        clear();
    }
}
