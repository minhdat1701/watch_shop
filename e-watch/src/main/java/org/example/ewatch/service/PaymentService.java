package org.example.ewatch.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.ewatch.dto.response.OrderResponse;
import org.example.ewatch.dto.response.PaymentResponse;
import org.example.ewatch.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface PaymentService {
    String createOrder(int total, String orderInfor, String urlReturn) throws UnsupportedEncodingException;
//    String payOrder(long amount) throws UnsupportedEncodingException;
    String generatePaymentUrl(Order order, String returnUrl) throws UnsupportedEncodingException;
    int orderReturn(HttpServletRequest request);
    void updatePaymentStatus(Long orderId, String paymentStatus);
}
