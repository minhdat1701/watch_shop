package org.example.ewatch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
//    private Long id;
//    private Long orderId;
    private Long productId;
    private String productName;
    private Double price;
    private int quantity;
    private Double totalPrice;
}
