package com.grocery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.Grocery;

public interface GroceryItemRepository extends JpaRepository<Grocery, Long> {
	
	

}
