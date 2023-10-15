package com.grocery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entities.Grocery;
import com.grocery.entities.UserGrocery;
import com.grocery.repository.GroceryItemRepository;
import com.grocery.repository.UserRepository;

@Service
public class GroceryService {
    @Autowired
    private GroceryItemRepository itemRepository;
 
    @Autowired
    private UserRepository userRepository;

    public List<Grocery> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Grocery> getAvailableItems() {
        return itemRepository.findAll().stream()
                .filter(item -> item.getStockQuantity() > 0)
                .collect(Collectors.toList());
    }

    public Grocery addItem(Grocery item) {
        return itemRepository.save(item);
    }

    public boolean removeItem(Long itemId) {
        itemRepository.deleteById(itemId);
        return true;
    }

    public Grocery updateItem(Long itemId, Grocery updatedItem) {
        if (itemRepository.existsById(itemId)) {
            updatedItem.setId(itemId);
            return itemRepository.save(updatedItem);
        }
        return null; // Item not found
    }

    public boolean manageInventory(Long itemId, int quantity) {
        Grocery item = itemRepository.findById(itemId).orElse(null);
        if (item != null) {
            item.setStockQuantity(item.getStockQuantity() + quantity);
            itemRepository.save(item);
            return true;
        }
        return false; // Item not found
    }
    

    
    public boolean placeOrder(String username, List<Long> itemIds) {
        UserGrocery user = userRepository.findByUsername(username);
        if (user != null && !user.isAdmin()) {
            List<Grocery> items = itemRepository.findAllById(itemIds);
            if (items.isEmpty()) {
               return false; // No items found
            }

            for (Grocery item : items) {
                if (item.getStockQuantity() <= 0) {
                    return false; // Insufficient stock for at least one item
                }
            }

            // If all checks pass,  can create an order and update item stocks here.
            return true;
        }
        return false; // User not found or is an Admin
    }
}