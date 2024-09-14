package org.example.ewatch.repository;

import org.example.ewatch.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder_Id(Long orderId);
    OrderItem findByOrder_IdAndProduct_Id(Long orderId, Long productId);
}
