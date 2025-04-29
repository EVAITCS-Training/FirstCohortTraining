package com.horrorcore.javaBurger.repositories;

import com.horrorcore.javaBurger.entities.MenuItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Iterator;
import java.util.UUID;

public interface MenuItemRepository extends CrudRepository<MenuItem, UUID> {
    Iterable<MenuItem> findAllByPriceBetween(double low, double high);
}
