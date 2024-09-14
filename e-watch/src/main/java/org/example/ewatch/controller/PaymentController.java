package org.example.ewatch.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ewatch.dto.PageRes;
import org.example.ewatch.dto.request.OrderUpdateStatus;
import org.example.ewatch.dto.response.OrderResponse;
import org.example.ewatch.dto.response.PaymentResponse;
import org.example.ewatch.entity.Order;
import org.example.ewatch.entity.OrderStatus;
import org.example.ewatch.mapper.OrderItemMapper;
import org.example.ewatch.mapper.OrderMapper;
import org.example.ewatch.service.Impl.PaymentServiceImpl;
import org.example.ewatch.service.OrderService;
import org.example.ewatch.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

import static org.example.ewatch.entity.OrderStatus.SUCCESS;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;

//    @GetMapping
//    public PageRes<PaymentResponse> getAllPayments(
//            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
//    {
//        Page<PaymentResponse> paymentPage = paymentService.getAllPayments(pageable);
//        return new PageRes<>(paymentPage);
//    }

    @GetMapping("/return")
    public ResponseEntity<?> handleVNpayReturn(HttpServletRequest request) {
        int result = paymentService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        Long orderId = Long.parseLong(orderInfo);
        OrderResponse orderResponse = orderService.getOrderById(orderId);

        if (result == 1) {
            // Payment success
            orderResponse.setStatus(OrderStatus.SUCCESS);
            orderResponse.setPaymentStatus("COMPLETED");
            return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
        } else if (result == 0) {
            // Payment failed
            orderResponse.setStatus(OrderStatus.CANCELLED);
            orderResponse.setPaymentStatus("FAILED");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(orderResponse);
        } else {
            // Invalid response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment response");
        }
    }

//    @GetMapping("/return")
//    public ResponseEntity<String> handleVNpayReturn(@RequestParam(name = "vnp_ResponseCode") String responseCode) {
//        if ("00".equals(responseCode)) {
//            return ResponseEntity.ok("Payment success");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment failed");
//        }
//    }
}
