package org.example.ewatch.controller;

import org.example.ewatch.dto.PageRes;
import org.example.ewatch.dto.request.OrderItemRequest;
import org.example.ewatch.dto.request.OrderRequest;
import org.example.ewatch.dto.request.OrderUpdateStatus;
import org.example.ewatch.dto.response.OrderItemResponse;
import org.example.ewatch.dto.response.OrderResponse;
import org.example.ewatch.entity.OrderStatus;
import org.example.ewatch.entity.User;
import org.example.ewatch.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @GetMapping
//    public List<OrderResponse> getAllOrders() {
//        return orderService.getAllOrders();
//    }

    @GetMapping
    public PageRes<OrderResponse> getAllOrders(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable)
    {
        Page<OrderResponse> ordersPage = orderService.getAllOrders(pageable);
        return new PageRes<>(ordersPage);

    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/{id}/items")
    public List<OrderItemResponse> getOrderItemsByOrderId(@PathVariable Long id) {
        return orderService.getOrderItemsByOrderId(id);
    }

//    @GetMapping("/users/{username}")
//    public List<OrderResponse> getOrdersByUsername(@PathVariable String username) {
//        return orderService.getOrderByUsername(username);
//    }
//
//    @GetMapping("/month/{month}")
//    public List<OrderResponse> getAllOrdersInMonth(@PathVariable int month) {
//        return orderService.getAllOrdersInMonth(month);
//    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest, @AuthenticationPrincipal User user) throws UnsupportedEncodingException {
        OrderResponse orderResponse = orderService.createOrder(user, orderRequest.getAddress(), orderRequest.getPhoneNumber(), orderRequest.getOrderItems());
        return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody OrderStatus orderStatus) {
        orderService.updateOrder(id, orderStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

}
