package org.example.ewatch.service.Impl;

import org.example.ewatch.dto.request.OrderItemRequest;
import org.example.ewatch.dto.response.OrderItemResponse;
import org.example.ewatch.entity.OrderItem;
import org.example.ewatch.exception.NotFoundException;
import org.example.ewatch.mapper.OrderItemMapper;
import org.example.ewatch.repository.OrderItemRepository;
import org.example.ewatch.repository.OrderRepository;
import org.example.ewatch.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderItemResponse> getAllOrderItems() {
        List<OrderItem> orderItemList = orderItemRepository.findAll();
        for (OrderItem orderItem : orderItemList) {
            orderItem.calculateTotalPrice();
        }
        return orderItemList.stream().map(orderItemMapper::toOrderItemResponse).collect(Collectors.toList());
    }

    @Override
    public OrderItemResponse getOrderItemById(Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(() -> new NotFoundException("OrderItem not found"));
        orderItem.calculateTotalPrice();
        return orderItemMapper.toOrderItemResponse(orderItem);
    }

    @Override
    public List<OrderItemResponse> getOrderDetailByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);
        for (OrderItem orderItem : orderItems) {
            orderItem.calculateTotalPrice();
        }
        return orderItems.stream().map(orderItemMapper::toOrderItemResponse).collect(Collectors.toList());
    }

//    @Override
//    public void createOrderItem(OrderItemRequest orderItemRequest) {
//        OrderItem existingOrderDetail = orderItemRepository.findByOrder_IdAndProduct_Id(orderItemRequest.getOrderId(), orderItemRequest.getProductId());
//        if (existingOrderDetail == null) {
//            OrderItem orderItem = orderItemMapper.toOrderItem(orderItemRequest);
//            orderItemRepository.save(orderItem);
////            orderItem.calculateTotalPrice();
//        }
//        else {
//            existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + orderItemRequest.getQuantity());
//            existingOrderDetail.calculateTotalPrice();
//            orderItemRepository.save(existingOrderDetail);
//        }
//    }

    @Override
    public void updateOrderItem(Long orderItemId, OrderItemRequest orderItemRequest) {
        OrderItem existingOrderItem = orderItemRepository.findById(orderItemId).get();
        if (existingOrderItem != null) {
            existingOrderItem = orderItemMapper.toOrderItem(orderItemRequest);
            orderItemRepository.save(existingOrderItem);
        }
    }

    @Override
    public void deleteOrderItem(Long orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }
}
