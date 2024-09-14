package org.example.ewatch.mapper;

import org.example.ewatch.dto.request.OrderRequest;
import org.example.ewatch.dto.response.OrderResponse;
import org.example.ewatch.entity.Order;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "orderItems", target = "orderItems")
    OrderResponse toOrderResponse(Order order);

    @AfterMapping
    default void setTotalPrice(Order order, @MappingTarget OrderResponse orderResponse) {
        order.setTotalPrice();
        orderResponse.setTotalPrice(order.getTotalPrice());
    }

//    @Mapping(source = "userId", target = "user.id")
//    @Mapping(source = "userName", target = "user.username")
    Order toOrder(OrderRequest orderRequest);
}
