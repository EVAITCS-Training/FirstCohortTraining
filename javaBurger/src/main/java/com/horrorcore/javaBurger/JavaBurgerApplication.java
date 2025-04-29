package com.horrorcore.javaBurger;

import com.horrorcore.javaBurger.entities.MenuItem;
import com.horrorcore.javaBurger.repositories.MenuItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Iterator;
import java.util.List;

@SpringBootApplication
public class JavaBurgerApplication implements CommandLineRunner {

	@Autowired
	private MenuItemRepository menuItemRepository;
	private Logger logger = LoggerFactory.getLogger(JavaBurgerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JavaBurgerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		menuItemRepository.saveAll(List.of(
		    new MenuItem("Classic Burger", 5.99),
		    new MenuItem("Cheeseburger", 6.49),
		    new MenuItem("Bacon Burger", 7.49),
		    new MenuItem("Veggie Burger", 5.49),
		    new MenuItem("Chicken Sandwich", 6.99),
		    new MenuItem("Fish Sandwich", 7.99),
		    new MenuItem("Double Cheeseburger", 8.49),
		    new MenuItem("BBQ Burger", 7.99),
		    new MenuItem("Mushroom Swiss Burger", 7.49),
		    new MenuItem("Spicy Chicken Sandwich", 6.99),
		    new MenuItem("Grilled Chicken Sandwich", 6.49),
		    new MenuItem("Turkey Burger", 6.99),
		    new MenuItem("Buffalo Chicken Sandwich", 7.49),
		    new MenuItem("Philly Cheesesteak", 8.99),
		    new MenuItem("Pulled Pork Sandwich", 7.99),
		    new MenuItem("BLT Sandwich", 5.99),
		    new MenuItem("Club Sandwich", 6.99),
		    new MenuItem("Grilled Cheese", 4.99),
		    new MenuItem("Patty Melt", 6.49),
		    new MenuItem("Breakfast Burger", 7.99),
		    new MenuItem("Avocado Burger", 8.49),
		    new MenuItem("Hawaiian Burger", 7.99),
		    new MenuItem("Chili Burger", 7.49),
		    new MenuItem("Sliders (3-pack)", 6.99),
		    new MenuItem("Kids Cheeseburger", 4.99)
		));

		Iterator<MenuItem> iterator = menuItemRepository.findAllByPriceBetween(6.0, 8.0).iterator();
		while (iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			logger.info("Menu Item: " + menuItem.name() + ", Price: " + menuItem.price());
		}
	}
}
