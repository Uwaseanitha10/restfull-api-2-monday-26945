package com.afanyu.question3_restaurant_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.afanyu.question3_restaurant_api.model.MenuItem;

@RestController
@RequestMapping("/api/menu/")
public class MenuController {
    private List<MenuItem> menuItems = new ArrayList<>();

    public MenuController() {
        menuItems.add(new MenuItem(1L, "Spring Rolls", "Vegetable rolls", 6.50, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Garlic Bread", "Toasted with herbs", 4.00, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Beef Burger", "Classic with fries", 15.00, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Pasta Carbonara", "Creamy sauce", 13.50, "Main Course", false));
        menuItems.add(new MenuItem(5L, "Chocolate Cake", "Dark cocoa", 7.00, "Dessert", true));
        menuItems.add(new MenuItem(6L, "Fruit Tart", "Seasonal fruits", 6.00, "Dessert", true));
        menuItems.add(new MenuItem(7L, "Iced Tea", "Freshly brewed", 3.00, "Beverage", true));
        menuItems.add(new MenuItem(8L, "Cappuccino", "Italian style", 4.50, "Beverage", true));
    }


}
