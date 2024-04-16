package com.kollu.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kollu.order.dto.CustomerOrder;
import com.kollu.order.dto.OrderEvent;
import com.kollu.order.entity.Order;
import com.kollu.order.entity.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class OrderController {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	private static final String NEW_ORDER_TOPIC ="new-order";
	
	@PostMapping("/orders")
	public void createOrder(@RequestBody CustomerOrder customerOrder) {
		log.info("CreateOrder -- Method Start");
		Order order = new Order();	
		order.setAmount(customerOrder.getAmount());
		order.setItem(customerOrder.getItem());
		order.setQuantity(customerOrder.getQuantity());
		order.setStatus("CREATED");
		log.debug("Order status:::: "+order.getStatus());
		try {
			order = repository.save(order);
			
			customerOrder.setOrderId(order.getId());
			OrderEvent orderEvent = new OrderEvent();
			orderEvent.setOrder(customerOrder);
			orderEvent.setType("ORDER_CREATED");
			log.debug("OrderEvent ::: "+ orderEvent);
			kafkaTemplate.send(NEW_ORDER_TOPIC, orderEvent);
			
			log.info("CreateOrder -- Method End");	
		} catch (Exception e) {
			order.setStatus("FAILED");
			order = repository.save(order);
		}
	}
}
	