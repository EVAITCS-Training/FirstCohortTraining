package com.horrorcore.repository;

import com.horrorcore.config.DatabaseConnection;
import com.horrorcore.entity.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository {
    private Connection conn;

    public MenuItemRepository() {
        this.conn = DatabaseConnection.getInstance().getConnection();
    }

    public MenuItem save(MenuItem menuItem) {
        String sql = "INSERT INTO jb_menu_item (name, price, quantity, size, is_meal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, menuItem.name());
            preparedStatement.setDouble(2, menuItem.price());
            preparedStatement.setInt(3, menuItem.quantity());
            preparedStatement.setString(4, String.valueOf(menuItem.size()));
            preparedStatement.setBoolean(5, menuItem.isMenu());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating menu item failed, no rows affected.");
            }
            return menuItem;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public MenuItem findByName(String name) {
        String sql = "SELECT id, name, price, quantity, size, is_menu FROM jb_menu_item WHERE name = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("size").charAt(0),
                        resultSet.getBoolean("is_menu")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<MenuItem> findAll() {
        String sql = "SELECT id, name, price, quantity, size, is_menu FROM jb_menu_item";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            List<MenuItem> menuItems = new ArrayList<>();
            while (resultSet.next()) {
                menuItems.add(new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("size").charAt(0),
                        resultSet.getBoolean("is_menu")
                ));
            }
            return menuItems;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<MenuItem> findAllByPrice(double price) {
        String sql = "SELECT id, name, price, quantity, size, is_menu FROM jb_menu_item WHERE price = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDouble(1, price);
            var resultSet = preparedStatement.executeQuery();
            List<MenuItem> menuItems = new ArrayList<>();
            while (resultSet.next()) {
                menuItems.add(new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("size").charAt(0),
                        resultSet.getBoolean("is_menu")
                ));
            }
            return menuItems;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public List<MenuItem> findAllByPriceRange(double minPrice, double maxPrice) {
        String sql = "SELECT id, name, price, quantity, size, is_menu FROM jb_menu_item WHERE price BETWEEN ? AND ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);
            var resultSet = preparedStatement.executeQuery();
            List<MenuItem> menuItems = new ArrayList<>();
            while (resultSet.next()) {
                menuItems.add(new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("size").charAt(0),
                        resultSet.getBoolean("is_menu")
                ));
            }
            return menuItems;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public MenuItem findById(long id) {
        String sql = "SELECT id, name, price, quantity, size, is_menu FROM jb_menu_item WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new MenuItem(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getString("size").charAt(0),
                        resultSet.getBoolean("is_menu")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void deleteById(long id) {
        String sql = "DELETE FROM jb_menu_item WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No menu item found with ID: " + id);
            } else {
                System.out.println("Menu item with ID: " + id + " deleted successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void update(MenuItem menuItem) {
        String sql = "UPDATE jb_menu_item SET name = ?, price = ?, quantity = ?, size = ?, is_menu = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, menuItem.name());
            preparedStatement.setDouble(2, menuItem.price());
            preparedStatement.setInt(3, menuItem.quantity());
            preparedStatement.setString(4, String.valueOf(menuItem.size()));
            preparedStatement.setBoolean(5, menuItem.isMenu());
            preparedStatement.setLong(6, menuItem.id());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No menu item found with ID: " + menuItem.id());
            } else {
                System.out.println("Menu item with ID: " + menuItem.id() + " updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateQuantity(long id, int quantity) {
        String sql = "UPDATE jb_menu_item SET quantity = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setLong(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No menu item found with ID: " + id);
            } else {
                System.out.println("Menu item with ID: " + id + " updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updatePrice(long id, double price) {
        String sql = "UPDATE jb_menu_item SET price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setDouble(1, price);
            preparedStatement.setLong(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No menu item found with ID: " + id);
            } else {
                System.out.println("Menu item with ID: " + id + " updated successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
