package com.kollu.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrder {

	private String item;
	private int quantity;
	private long orderId;
	
	private double amount;
	private String paymentMode;
	
	private String address;
}
