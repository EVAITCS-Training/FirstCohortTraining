package com.horrorcore.javaBurger.services;

import com.horrorcore.javaBurger.entities.MenuItem;

import java.util.List;

public interface MenuItemService {
    MenuItem createMenuItem(MenuItem menuItem);
    MenuItem updateMenuItem(MenuItem menuItem);
    void deleteMenuItem(String id);
    MenuItem getMenuItemById(String id);
    List<MenuItem> getAllMenuItems();
    List<MenuItem> getMenuItemsByPriceRange(double low, double high);
}
