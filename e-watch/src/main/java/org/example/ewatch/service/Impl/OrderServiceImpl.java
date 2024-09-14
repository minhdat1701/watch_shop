package org.example.ewatch.service.Impl;

import org.example.ewatch.dto.request.OrderItemRequest;
import org.example.ewatch.dto.request.OrderUpdateStatus;
import org.example.ewatch.dto.response.OrderItemResponse;
import org.example.ewatch.dto.response.OrderResponse;
import org.example.ewatch.entity.*;
import org.example.ewatch.exception.NotFoundException;
import org.example.ewatch.mapper.OrderItemMapper;
import org.example.ewatch.mapper.OrderMapper;
import org.example.ewatch.repository.*;
import org.example.ewatch.service.OrderService;
import org.example.ewatch.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentService paymentService;

//    @Override
//    public List<OrderResponse> getAllOrders() {
//        List<Order> orders = orderRepository.findAll();
//        for (Order order : orders) {
//            order.setTotalPrice();
//        }
//        return orders.stream().map(orderMapper::toOrderResponse).collect(Collectors.toList());
//    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable).map(orderMapper::toOrderResponse);
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new NotFoundException("Order not found"));
        order.setTotalPrice();
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public List<OrderItemResponse> getOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrder_Id(orderId);
        for (OrderItem orderItem : orderItems) {
            orderItem.calculateTotalPrice();
        }
        return orderItems.stream().map(orderItemMapper::toOrderItemResponse).collect(Collectors.toList());
    }

    @Override
    public OrderResponse createOrder(User user, String address, String phoneNumber, List<OrderItemRequest> orderItemRequests) throws UnsupportedEncodingException {
        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.NEW);
        order.setAddress(address);
        order.setPhoneNumber(phoneNumber);
        orderRepository.save(order);
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : orderItemRequests) {
            Product product = productRepository.findById(orderItemRequest.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            if (product.getQuantity() < orderItemRequest.getQuantity()) {
                throw new NotFoundException("Insufficient stock for product " + product.getName());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.calculateTotalPrice();
            // Update product quantity
            product.setQuantity(product.getQuantity() - orderItemRequest.getQuantity());
            productRepository.save(product);

            orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        order.setTotalPrice();
        orderRepository.save(order);
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem);
        }

        String returnUrl = "http://localhost:8080";
        String paymentUrl = paymentService.generatePaymentUrl(order, returnUrl);
        OrderResponse orderResponse = orderMapper.toOrderResponse(order);
        orderResponse.setPaymentStatus("PROCESSING");
        orderResponse.setPaymentUrl(paymentUrl);
        return orderResponse;
    }

    @Override
    public void updateOrder(Long orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Order with id " + orderId + " not found"));
        order.setStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
