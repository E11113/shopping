package com.example.shopping.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cartItem")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This is required")
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull(message = "This is required")
    @Column(name = "product_id")
    private Long productId;

    @NotNull(message = "This is required")
    @Column(name = "quantity")
    private Double quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
