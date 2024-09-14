package org.example.ewatch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    //thêm
    private String address;
    private String phoneNumber;
    //
    private Double totalPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    public void setTotalPrice() {
        if (orderItems != null && !orderItems.isEmpty()) {
            this.totalPrice = orderItems.stream().filter(orderItem -> orderItem.getOrder().getId().equals(this.getId()))
                    .mapToDouble(OrderItem::getTotalPrice).sum();
        } else {
            this.totalPrice = 0.0;
        }
    }
}
