package com.horrorcore;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private int count = 0;

    public void incrementCount() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
