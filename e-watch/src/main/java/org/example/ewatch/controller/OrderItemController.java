package org.example.ewatch.controller;

import org.example.ewatch.dto.request.OrderItemRequest;
import org.example.ewatch.dto.response.OrderItemResponse;
import org.example.ewatch.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemResponse> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemResponse getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @GetMapping("/order/{id}")
    public List<OrderItemResponse> getOrderDetailByOrderId(@PathVariable Long id) {
        return orderItemService.getOrderDetailByOrderId(id);
    }

//    @PostMapping
//    public void createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
//        orderItemService.createOrderItem(orderItemRequest);
//    }

    @PutMapping("/{id}")
    public void updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequest orderItemRequest) {
        orderItemService.updateOrderItem(id, orderItemRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id) {
        orderItemService.deleteOrderItem(id);
    }

}
