package com.horrorcore;

public class NestedForLoop {
    public static void main(String[] args) {
        int num = 25;
        //With just the outer loop it is Big O(n)
        OUTER_LOOP:for (int i = 0; i < num; i++) {

            INNER_LOOP:for (int j = 0; j < num; j++) {
                //This is a nested loop, so it is Big O(n^2)
                System.out.println("i: " + i + ", j: " + j);
                if (j == 5) {
                    break OUTER_LOOP;
                }
            }

        }
    }
}
