package com.kollu.order.ms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kollu.order.dto.OrderEvent;
import com.kollu.order.entity.Order;
import com.kollu.order.entity.OrderRepository;

@Component
public class ReverseOrder {
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	private static final String REVERSED_ORDERS_TOPIC = "reversed-orders";
	private static final String REVERSED_ORDERS_GROUP = "orders-group";
	
	@KafkaListener(topics = REVERSED_ORDERS_TOPIC, groupId = REVERSED_ORDERS_GROUP)
	public void reverseOrder(String event) {
		System.out.println("Reverse order event :: " + event);
		
		try {
			OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);
			Optional<Order> order = orderRepository.findById(orderEvent.getOrder().getOrderId());
			
			order.ifPresent(o -> {
				o.setStatus("FAILED");
				orderRepository.save(o);
			});
			
		} catch (Exception e) {
			System.out.println("Excepton Occured while reverting order details");
		}
	}

}
