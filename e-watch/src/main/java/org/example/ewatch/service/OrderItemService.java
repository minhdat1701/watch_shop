package org.example.ewatch.service;

import org.example.ewatch.dto.request.OrderItemRequest;
import org.example.ewatch.dto.response.OrderItemResponse;

import java.util.List;

public interface OrderItemService {
    List<OrderItemResponse> getAllOrderItems();
    OrderItemResponse getOrderItemById(Long orderItemId);
    List<OrderItemResponse> getOrderDetailByOrderId(Long orderId);
//    void createOrderItem(OrderItemRequest orderItemRequest);
    void updateOrderItem(Long orderItemId, OrderItemRequest orderItemRequest);
    void deleteOrderItem(Long orderItemId);
}
