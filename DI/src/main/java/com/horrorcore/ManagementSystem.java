package com.horrorcore;

import java.util.Scanner;

public class ManagementSystem {
    private Scanner scan;

    public ManagementSystem(Scanner scan) {
        this.scan = scan;
    }

    public void start() {
        System.out.println("Welcome to the HorrorCore Management System!");
        System.out.println("Please select an option:");
        System.out.println("1. Add a new horror movie");
        System.out.println("2. View all horror movies");
        System.out.println("3. Exit");

        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                addMovie();
                break;
            case 2:
                viewMovies();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                start();
                break;
        }
    }

    public void addMovie() {
        System.out.println("Adding a new horror movie...");
        // Logic to add a new horror movie
        // For example, you can ask for the movie title, director, etc.

        System.out.println("Movie added successfully!");
        start();
    }

    public void viewMovies() {
        System.out.println("Viewing all horror movies...");
        // Logic to view all horror movies
        // For example, you can display a list of movies

        System.out.println("End of movie list.");
        start();
    }
}
