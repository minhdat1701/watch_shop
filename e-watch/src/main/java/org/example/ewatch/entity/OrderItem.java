package org.example.ewatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double price;

    private int quantity;

    private Double totalPrice;

    public void setPrice() {
        if (product != null) {
            this.price = product.getPrice();
        }
    }

    public Double getPrice() {
        if (product != null) {
            return product.getPrice();
        }
        return 0.0;
    }

    public void calculateTotalPrice() {
        this.totalPrice = product.getPrice() * this.quantity;
    }

    public Double getTotalPrice() {
        return product.getPrice() * this.quantity;
    }
}
