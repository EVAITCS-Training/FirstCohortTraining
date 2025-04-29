package com.horrorcore.repository;

import com.horrorcore.config.DatabaseConnection;
import com.horrorcore.entity.MenuItem;
import com.horrorcore.entity.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private Connection conn;
    private MenuItemRepository menuItemRepository;

    public OrderRepository(MenuItemRepository menuItemRepository) {
        this.conn = DatabaseConnection.getInstance().getConnection();
        this.menuItemRepository = menuItemRepository;
    }

    public Order save(Order order) {
        String sql = "INSERT INTO jb_order (user_id, order_at, total, payment) VALUES (?, ?, ?, ?)";
        try (var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.userId());
            preparedStatement.setObject(2, order.orderAt());
            preparedStatement.setDouble(3, order.total());
            preparedStatement.setString(4, order.payment());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            } else {
                try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        order.setOrderNumber(generatedKeys.getInt(1));
                        saveIntoOrderItem(order);
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }
            return order;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public Order findByOrderNumber(int orderNumber) {
        String sql = "SELECT order_number, user_id, order_at, total, payment FROM jb_order WHERE order_number = ?";
        try (var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderNumber);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Order(
                        resultSet.getInt("order_number"),
                        resultSet.getInt("user_id"),
                        resultSet.getObject("order_at", java.time.LocalDateTime.class),
                        resultSet.getDouble("total"),
                        resultSet.getString("payment"),
                        getMenuItemsByOrderNumber(resultSet.getInt("order_number"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private void saveIntoOrderItem(Order order) {
        String sql = "INSERT INTO jb_order_menu_item (order_number, menu_item_id) VALUES (?, ?)";
        try (var preparedStatement = conn.prepareStatement(sql)) {
            for (var item : order.menuItems()) {
                preparedStatement.setLong(1, order.orderNumber());
                preparedStatement.setLong(2, item.id());
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating order item failed, no rows affected.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private List<MenuItem> getMenuItemsByOrderNumber(int orderNumber) {
        String sql = "SELECT menu_item_id FROM jb_order_menu_item WHERE order_number = ?";
        try (var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setLong(1, orderNumber);
            var resultSet = preparedStatement.executeQuery();
            List<MenuItem> menuItems = new ArrayList<>();
            while (resultSet.next()) {
                var menuItem = menuItemRepository.findById(resultSet.getLong("menu_item_id"));
                if (menuItem != null) {
                    menuItems.add(menuItem);
                }
            }
            return menuItems;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
