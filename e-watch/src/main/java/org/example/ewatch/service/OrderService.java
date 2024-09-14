package org.example.ewatch.service;

import org.example.ewatch.dto.request.OrderItemRequest;
import org.example.ewatch.dto.request.OrderRequest;
import org.example.ewatch.dto.request.OrderUpdateStatus;
import org.example.ewatch.dto.response.OrderItemResponse;
import org.example.ewatch.dto.response.OrderResponse;
import org.example.ewatch.dto.response.ProductResponse;
import org.example.ewatch.entity.Order;
import org.example.ewatch.entity.OrderItem;
import org.example.ewatch.entity.OrderStatus;
import org.example.ewatch.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {
//    public List<OrderResponse> getAllOrders();
    Page<OrderResponse> getAllOrders(Pageable pageable);

    public OrderResponse getOrderById(Long orderId);
    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId);
//    List<OrderResponse> getOrderByUsername(String username);
//    List<OrderResponse> getAllOrdersInMonth(int month);
    OrderResponse createOrder(User user, String address, String phoneNumber, List<OrderItemRequest> orderItemRequests) throws UnsupportedEncodingException;
    public void updateOrder(Long orderId, OrderStatus orderStatus);
    public void deleteOrder(Long orderId);
}
