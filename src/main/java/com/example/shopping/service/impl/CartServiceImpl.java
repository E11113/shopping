package com.example.shopping.service.impl;

import com.example.shopping.config.RabbitMQProperties;
import com.example.shopping.model.CartItem;
import com.example.shopping.model.Customer;
import com.example.shopping.model.Product;
import com.example.shopping.repository.CustomerRepository;
import com.example.shopping.repository.OrderRepository;
import com.example.shopping.repository.ProductRepository;
import com.example.shopping.service.CartService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RabbitMQProperties rabbitMQProperties;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity addCartItemToQueue(CartItem cartItem) {

        try {
            customerRepository.findById(cartItem.getCustomerId()).get();
            productRepository.findById(cartItem.getProductId()).get();

            rabbitTemplate.convertAndSend(rabbitMQProperties.getExchangeName(), rabbitMQProperties.getRoutingKey(), cartItem);

        } catch (NoSuchElementException ex) {
            return new ResponseEntity("Either Customer or Product is not registered!", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity("Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RabbitListener(queues = "${rabbitmq.queueName}", returnExceptions = "true")
    public void receiveAddedCartItem(CartItem cartItem) {

        try {
            orderRepository.saveAndFlush(cartItem);
        } catch(Exception e) {
            System.out.println("Internal server error!");
        }
    }
}
