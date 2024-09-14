package org.example.ewatch.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.ewatch.entity.OrderStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateStatus {
    private OrderStatus status;
}
