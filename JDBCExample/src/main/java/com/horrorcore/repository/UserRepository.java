package com.horrorcore.repository;

import com.horrorcore.config.DatabaseConnection;
import com.horrorcore.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private Connection conn;

    public UserRepository() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public User save(User user) {
        if (user.getPassword().isBlank() || user.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username and password cannot be blank");
        }
        String sql = "INSERT INTO jb_user (username, password, role) VALUE (?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            return user;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT id, username, password, role FROM jb_user WHERE username = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<User> findAllByRole(String role) {
        String sql = "SELECT id, username, password, role FROM jb_user WHERE role = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, role);
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                ));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM jb_user WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No user found with ID: " + id);
            } else {
                System.out.println("User with ID: " + id + " deleted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void update(User user) {
        String sql = "UPDATE jb_user SET username = ?, password = ?, role = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setLong(4, user.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No user found with ID: " + user.getId());
            } else {
                System.out.println("User with ID: " + user.getId() + " updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
