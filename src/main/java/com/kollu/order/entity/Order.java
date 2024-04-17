package com.kollu.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Order_Table")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private long id;
	
	@Column(name = "Order_ITEM")
	private String item;
	
	@Column(name = "Order_Quantity")
	private int quantity;
	
	@Column(name = "Order_Amount")
	private double amount;
	
	@Column(name = "Order_Status")
	private String status;

}
