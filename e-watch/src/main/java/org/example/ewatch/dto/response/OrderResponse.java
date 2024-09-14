package org.example.ewatch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ewatch.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long userId;
    private String userName;
    private OrderStatus status;
    private String address;
    private String phoneNumber;
    private Double totalPrice;
    private String paymentStatus;
    private String paymentUrl;
    private LocalDateTime createdAt;
    private Set<OrderItemResponse> orderItems;
}
