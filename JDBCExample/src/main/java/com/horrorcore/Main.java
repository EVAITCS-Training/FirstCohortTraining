package com.horrorcore;

import com.horrorcore.config.DatabaseConnection;
import com.horrorcore.entity.MenuItem;
import com.horrorcore.entity.User;
import com.horrorcore.repository.MenuItemRepository;
import com.horrorcore.repository.OrderRepository;
import com.horrorcore.repository.UserRepository;

import java.sql.SQLException;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        UserRepository userRepository = new UserRepository();
        MenuItemRepository menuItemRepository = new MenuItemRepository();
        OrderRepository orderRepository = new OrderRepository(menuItemRepository);
        Scanner scanner = new Scanner(System.in);
        if (connection.getConnection().isValid(30)) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }

//        User user = new User();
//        user.setUsername("");
//        user.setPassword("testPassword");
//        user.setRole("CUSTOMER");
//        User savedUser = userRepository.save(user);
//        if (savedUser != null) {
//            System.out.println("User saved with ID: " + savedUser.getId());
//        } else {
//            System.out.println("Failed to save user.");
//        }

//        User user = userRepository.findByUsername("testUser");
//        if (user != null) {
//            System.out.println("User found: " + user.getUsername());
//        } else {
//            System.out.println("User not found.");
//        }
//
//        connection.getConnection().close();

//        List<User> customers = userRepository.findAllByRole("CUSTOMER");
//        if (customers != null && !customers.isEmpty()) {
//            System.out.println("Customers found:");
//            for (User customer : customers) {
//                System.out.println("ID: " + customer.getId() + ", Username: " + customer.getUsername());
//            }
//        } else {
//            System.out.println("No customers found.");
//        }

        //userRepository.deleteById(9L);

        while (true) {
            System.out.println("""
                    Welcome to the Java Burger App!
                    1. Create User
                    2. Find User
                    3. Create Menu Item
                    4. Find Menu Item
                    5. Create Order
                    6. Find Order
                    10. Exit
                    """);
            System.out.print("Please select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            switch (option) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role: ");
                    String role = scanner.nextLine();
                    User user = new User(username, password, role);
                    userRepository.save(user);
                }
                case 2 -> {
                    System.out.print("Enter username to find: ");
                    String username = scanner.nextLine();
                    User user = userRepository.findByUsername(username);
                    if (user != null) {
                        System.out.println("User found: " + user.getUsername());
                    } else {
                        System.out.println("User not found.");
                    }
                }
                case 3 -> {
                    System.out.println("What is the Item Name?");
                    String name = scanner.nextLine();
                    System.out.println("What is the Item Price?");
                    double price = scanner.nextDouble();
                    System.out.println("What is the Item Quantity?");
                    int quantity = scanner.nextInt();
                    System.out.println("What is the Item Size? (SMALL, MEDIUM, LARGE)");
                    String size = scanner.next();
                    char sizeChar = size.charAt(0);
                    System.out.println("Is it a Menu Item? (true/false)");
                    boolean isMenu = scanner.nextBoolean();
                    MenuItem menuItem = new MenuItem(name, price, quantity, sizeChar, isMenu);
                    menuItem = menuItemRepository.save(menuItem);
                    System.out.println("Menu item saved with ID: " + menuItem.id());
                }
                case 4 -> {
                    System.out.println("Enter Menu Item Name to find: ");
                    String name = scanner.nextLine();
                    MenuItem menuItem = menuItemRepository.findByName(name);
                    if (menuItem != null) {
                        System.out.println("Menu item found:");
                        System.out.println(menuItem.toString());
                    } else {
                        System.out.println("No menu items found.");
                    }
                }
                case 5 -> {
                    // Implement order creation
                }
                case 6 -> {
                    // Implement order finding
                }
                case 10 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}