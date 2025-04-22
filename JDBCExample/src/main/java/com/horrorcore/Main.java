package com.horrorcore;

import com.horrorcore.config.DatabaseConnection;

import java.sql.SQLException;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection connection = DatabaseConnection.getInstance();
        if (connection.getConnection().isValid(30)) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Connection failed.");
        }
    }
}