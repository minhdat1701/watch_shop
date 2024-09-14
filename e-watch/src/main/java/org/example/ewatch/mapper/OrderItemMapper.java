package org.example.ewatch.mapper;

import org.example.ewatch.dto.request.OrderItemRequest;
import org.example.ewatch.dto.response.OrderItemResponse;
import org.example.ewatch.entity.OrderItem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItemMapper INSTANCE = Mappers.getMapper(OrderItemMapper.class);

//    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "product.price", target = "price")
    @Mapping(source = "quantity", target = "quantity")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);

    @AfterMapping
    default void calculateTotalPrice(@MappingTarget OrderItem orderItem) {
        if (orderItem.getPrice() != null && orderItem.getQuantity() > 0) {
            orderItem.calculateTotalPrice();
        }
    }

//    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "quantity", target = "quantity")
    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

}
