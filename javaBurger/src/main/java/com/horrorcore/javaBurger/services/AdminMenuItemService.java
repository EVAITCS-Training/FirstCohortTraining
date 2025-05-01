package com.horrorcore.javaBurger.services;

import com.horrorcore.javaBurger.entities.MenuItem;
import com.horrorcore.javaBurger.exceptions.MenuItemNotFoundException;
import com.horrorcore.javaBurger.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("adminMenuItemService")
public class AdminMenuItemService implements MenuItemService {
    // This service is for admins, so it should allow creating, updating, and deleting menu items.
    private final MenuItemRepository menuItemRepository;

    public AdminMenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    public MenuItem updateMenuItem(MenuItem menuItem) {
        if(menuItem.id() == null) {
            throw new MenuItemNotFoundException("Menu item ID cannot be null");
        }
        return menuItemRepository.save(menuItem);
    }

    @Override
    public void deleteMenuItem(String id) {
        menuItemRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public MenuItem getMenuItemById(String id) {
        return menuItemRepository.findById(UUID.fromString(id)).orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with id: " + id));
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return (List<MenuItem>) menuItemRepository.findAll();
    }

    @Override
    public List<MenuItem> getMenuItemsByPriceRange(double low, double high) {
        return (List<MenuItem>) menuItemRepository.findAllByPriceBetween(low, high);
    }
}
