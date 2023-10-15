package com.grocery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grocery.entities.UserGrocery;

public interface UserRepository extends JpaRepository<UserGrocery, Long>{

	
	 UserGrocery findByUsername(String username);
	
	
}
