package com.anitha.question3_restaurant_api.controller.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anitha.question3_restaurant_api.model.restaurant.MenuItem;

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

    @GetMapping
    public List<MenuItem> getAllItems() {
        return menuItems;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getItemById(@PathVariable Long id) {
        return menuItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/category/{category}")
    public List<MenuItem> getByCategory(@PathVariable String category) {
        return menuItems.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

  
    @GetMapping("/available")
    public List<MenuItem> getAvailableItems(@RequestParam boolean available) {
        return menuItems.stream()
                .filter(item -> item.isAvailable() == available)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<MenuItem> searchByName(@RequestParam String name) {
        return menuItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem item) {
        menuItems.add(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable()); 
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean removed = menuItems.removeIf(item -> item.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
