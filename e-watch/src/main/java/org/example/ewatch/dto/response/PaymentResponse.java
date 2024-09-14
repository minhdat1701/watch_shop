package org.example.ewatch.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse{
    private Long orderId;
    private Double amount;
    private String method = "VNPAY";
    private String status;
}
