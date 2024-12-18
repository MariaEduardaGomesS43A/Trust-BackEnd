package com.ibeus.Comanda.Digital.repository;

import com.ibeus.Comanda.Digital.model.Order;
import com.ibeus.Comanda.Digital.model.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByStatus(OrderStatus status);
}
