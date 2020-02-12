package com.example.shopping.repository;

import com.example.shopping.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<CartItem, Long>  {
}
