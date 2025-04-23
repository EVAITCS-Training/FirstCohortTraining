package com.horrorcore;

import com.horrorcore.config.DatabaseConnection;
import com.horrorcore.entity.User;
import com.horrorcore.repository.UserRepository;

import java.sql.SQLException;
import java.util.Deque;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        UserRepository userRepository = new UserRepository();
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

        userRepository.deleteById(9L);
    }
}