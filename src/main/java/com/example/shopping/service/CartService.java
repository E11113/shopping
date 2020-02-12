package com.example.shopping.service;

import com.example.shopping.model.CartItem;
import org.springframework.http.ResponseEntity;

public interface CartService {

    ResponseEntity addCartItemToQueue(CartItem cartItem);
}
