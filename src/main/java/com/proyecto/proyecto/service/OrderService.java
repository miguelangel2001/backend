package com.proyecto.proyecto.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.proyecto.proyecto.entity.Order;
import com.proyecto.proyecto.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepo;

    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order create(Order order) {
        order.setDate(LocalDateTime.now());
        order.setStatus("PENDING");
        return orderRepo.save(order);
    }
}
