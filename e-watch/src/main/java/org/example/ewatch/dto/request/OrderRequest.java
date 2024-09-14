package org.example.ewatch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ewatch.entity.OrderStatus;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String address;
    private String phoneNumber;
    private List<OrderItemRequest> orderItems;
}
