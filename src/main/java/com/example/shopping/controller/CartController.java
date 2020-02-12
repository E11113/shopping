package com.example.shopping.controller;

import com.example.shopping.model.CartItem;
import com.example.shopping.service.CartService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CartService cartService;

    @PostMapping("addItem")
    public ResponseEntity addCartItem(@RequestBody CartItem cartItem) {
        return cartService.addCartItemToQueue(cartItem);
    }
}
