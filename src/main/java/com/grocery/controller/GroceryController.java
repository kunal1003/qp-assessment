package com.grocery.controller;

import java.security.Principal;

import java.util.List;

//import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entities.Grocery;
import com.grocery.entities.UserGrocery;
import com.grocery.repository.UserRepository;
import com.grocery.service.GroceryService;

@RestController
@RequestMapping("/api/grocery")
public class GroceryController {
    @Autowired
    private GroceryService groceryService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/items")
    public List<Grocery> getAllItems() {
        return groceryService.getAllItems();
    }

    @GetMapping("/available-items")
    public List<Grocery> getAvailableItems() {
        return groceryService.getAvailableItems();
    }

    @PostMapping("/items1")
    public Grocery addItem(@RequestBody Grocery item) {
        return groceryService.addItem(item);
    }

    @DeleteMapping("/items/{itemId}")
    public boolean removeItem(@PathVariable Long itemId) {
        return groceryService.removeItem(itemId);
    }

    @PutMapping("/items/{itemId}")
    public Grocery updateItem(@PathVariable Long itemId, @RequestBody Grocery updatedItem) {
        return groceryService.updateItem(itemId, updatedItem);
    }

    @PostMapping("/manage-inventory/{itemId}/{quantity}")
    public boolean manageInventory(@PathVariable Long itemId, @PathVariable int quantity) {
        return groceryService.manageInventory(itemId, quantity);
    }

    
    @PostMapping("/place-order/{username}")
    public boolean placeOrder(@PathVariable String username, @RequestBody List<Long> itemIds) {
        return groceryService.placeOrder(username, itemIds);
    }
}
