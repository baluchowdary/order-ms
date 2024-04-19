package com.kollu.order.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kollu.order.dto.CustomerOrder;
import com.kollu.order.dto.OrderEvent;
import com.kollu.order.entity.Order;
import com.kollu.order.entity.OrderRepository;
import com.kollu.order.exception.RecordNotFoundException;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins= "http://localhost:4200")
@RestController
@RequestMapping("/api")
@Slf4j
public class OrderController {

	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private KafkaTemplate<String, OrderEvent> kafkaTemplate;
	
	private static final String NEW_ORDER_TOPIC ="new-order";
	
	/*
	 * @PostMapping("/createOrders") public void createOrder(@RequestBody
	 * CustomerOrder customerOrder) { log.info("CreateOrder -- Method Start");
	 * log.debug("customerOrder :::::" + customerOrder.getItem()); Order order = new
	 * Order(); order.setAmount(customerOrder.getAmount());
	 * order.setItem(customerOrder.getItem());
	 * order.setQuantity(customerOrder.getQuantity()); order.setStatus("CREATED");
	 * log.debug("Order status:::: "+order.getStatus()); try { order =
	 * repository.save(order);
	 * 
	 * customerOrder.setOrderId(order.getId()); OrderEvent orderEvent = new
	 * OrderEvent(); orderEvent.setOrder(customerOrder);
	 * orderEvent.setType("ORDER_CREATED"); log.debug("OrderEvent ::: "+
	 * orderEvent); kafkaTemplate.send(NEW_ORDER_TOPIC, orderEvent);
	 * 
	 * log.info("CreateOrder -- Method End"); } catch (Exception e) {
	 * order.setStatus("FAILED"); order = repository.save(order); } }
	 */
	

	@PostMapping("/createOrders")
	public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder customerOrder) {
		log.info("CreateOrder -- Method Start");
		Order order = new Order();
		order.setAmount(customerOrder.getAmount());
		order.setItem(customerOrder.getItem());
		order.setQuantity(customerOrder.getQuantity());
		order.setStatus("CREATED");
		
		log.debug("Order status:::: " + order.getStatus());
		try {
			order = repository.save(order);

			customerOrder.setOrderId(order.getId());
			OrderEvent orderEvent = new OrderEvent();
			orderEvent.setOrder(customerOrder);
			orderEvent.setType("ORDER_CREATED");
			
			log.debug("OrderEvent ::: " + orderEvent);
			kafkaTemplate.send(NEW_ORDER_TOPIC, orderEvent);

			log.info("CreateOrder -- Method End");
		} catch (Exception e) {
			//log.error(e.getMessage());
			order.setStatus("FAILED");
			order = repository.save(order);
		}
		return new ResponseEntity<CustomerOrder>(HttpStatus.CREATED);
		
	}

	
	
	@GetMapping("/getOrderDetails")
	public ResponseEntity<List<Order>> getOrderDetails(){
		log.info("getOrderDetails - start method"); 
		try {
			
		List<Order> orders = new ArrayList<Order>();  
		repository.findAll().forEach(orders::add);
		
		if (orders.isEmpty()) { 
			throw new RecordNotFoundException("Order details not avilable at this time.");
		}
		return new ResponseEntity<>(orders, HttpStatus.OK); 
		
	} catch (Exception e) {
		log.error("OrderController - getOrderDetails - Error :: " +e.getMessage()); 
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteOrderDetails(@PathVariable("id") long orderid) {
		log.info("OrderController - deleteOrderDetails method start");
		
		try {
			
			repository.deleteById(orderid);
			log.info("OrderController - deleteOrderDetails deleted");
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("OrderController - deleteOrderDetails - Error :: " +e.getMessage()); 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
	