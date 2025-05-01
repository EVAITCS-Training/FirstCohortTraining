package com.horrorcore.javaBurger.controllers;

import com.horrorcore.javaBurger.entities.MenuItem;
import com.horrorcore.javaBurger.services.MenuItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class MenuItemController {
    // This controller will be setup to handle requests related to menu items.
    // It will delegate the actual work to the appropriate service based on the user's role.
    // For example, if the user is an admin, it will use the AdminMenuItemService.
    // If the user is a customer, it will use the CustomerMenuItemService.
    private final MenuItemService customerMenuItemService;
    private final MenuItemService adminMenuItemService;

    public MenuItemController(MenuItemService customerMenuItemService, @Qualifier("adminMenuItemService") MenuItemService adminMenuItemService) {
        this.customerMenuItemService = customerMenuItemService;
        this.adminMenuItemService = adminMenuItemService;
    }

    private MenuItemService getService() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return adminMenuItemService;
        }
        return customerMenuItemService;
        // For now, we will just return the customer service.
        // In a real application, you would check the user's role and return the appropriate service.
//        return adminMenuItemService;
    }

    @GetMapping(value = {"", "/", "/menu"})
    public String menu(Model model) {
        List<MenuItem> menuItems = getService().getAllMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "menu";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("low", 0);
        model.addAttribute("high", 100);
        return "search";
    }

    @PostMapping("/search")
    public String search(@RequestParam double low, @RequestParam double high, Model model) {
        List<MenuItem> menuItems = getService().getMenuItemsByPriceRange(low, high);
        model.addAttribute("menuItems", menuItems);
        return "menu";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("menuItem", new MenuItem());
        return "create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("menuItem") @Valid MenuItem menuItem, Errors errors) {
        if (errors.hasErrors()) {
            return "create";
        }
        MenuItem createdMenuItem = getService().createMenuItem(menuItem);
        return "redirect:/menu";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable String id, Model model) {
        MenuItem menuItem = getService().getMenuItemById(id);
        model.addAttribute("menuItem", menuItem);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable String id, @ModelAttribute("menuItem") @Valid MenuItem menuItem, Errors errors) {
        if (errors.hasErrors()) {
            return "update";
        }
        menuItem.setId(UUID.fromString(id));
        getService().updateMenuItem(menuItem);
        return "redirect:/menu";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        getService().deleteMenuItem(id);
        return "redirect:/menu";
    }
}
