package com.horrorcore.javaBurger.services;

import com.horrorcore.javaBurger.entities.MenuItem;
import com.horrorcore.javaBurger.exceptions.AccessDeniedException;
import com.horrorcore.javaBurger.exceptions.MenuItemNotFoundException;
import com.horrorcore.javaBurger.repositories.MenuItemRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Primary
public class CustomerMenuItemService implements MenuItemService {
    // This service is for customers, so it should not allow creating, updating, or deleting menu items.
    private final MenuItemRepository menuItemRepository;

    public CustomerMenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItem menuItem) {
        throw new AccessDeniedException("You do not have permission to create a menu item.");
    }

    @Override
    public MenuItem updateMenuItem(MenuItem menuItem) {
        throw new AccessDeniedException("You do not have permission to update a menu item.");
    }

    @Override
    public void deleteMenuItem(String id) {
        throw new AccessDeniedException("You do not have permission to delete a menu item.");
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
