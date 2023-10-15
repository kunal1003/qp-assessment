package com.grocery.entities;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GroceryItem")
public class Grocery {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ID")
	    private Long id;
		@Column(name="NAME")
	    private String name;
		@Column(name="PRICE")
	    private double price;
		@Column(name="STOCKQUANTITY")	
	    private int stockQuantity;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

	 	@Override
	public String toString() {
		return "groceryItem [id=" + id + ", name=" + name + ", price=" + price + ", stockQuantity=" + stockQuantity
				+ "]";
	}
	    

}
