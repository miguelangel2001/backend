package com.proyecto.proyecto.service;

import com.proyecto.proyecto.dto.OrderItemRequest;
import com.proyecto.proyecto.entity.*;
import com.proyecto.proyecto.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepo;
    private final OrderRepository orderRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public OrderItemService(
            OrderItemRepository orderItemRepo,
            OrderRepository orderRepo,
            ProductRepository productRepo,
            UserRepository userRepo) {

        this.orderItemRepo = orderItemRepo;
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    /**
     * Agregar item a una orden
     */
    public OrderItem addItem(Long orderId, OrderItemRequest request, String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No autorizado");
        }

        Product product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no existe"));

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Stock insuficiente");
        }

        product.setStock(product.getStock() - request.getQuantity());
        productRepo.save(product);

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(request.getQuantity());
        item.setPrice(product.getPrice());

        return orderItemRepo.save(item);
    }

    /**
     * Listar items por orden
     */
    public List<OrderItem> getItemsByOrder(Long orderId, String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No autorizado");
        }

        return orderItemRepo.findByOrderId(orderId);
    }

    /**
     * Eliminar item
     */
    public void deleteItem(Long itemId, String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        OrderItem item = orderItemRepo.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        if (!item.getOrder().getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No autorizado");
        }

        Product product = item.getProduct();
        product.setStock(product.getStock() + item.getQuantity());
        productRepo.save(product);

        orderItemRepo.delete(item);
    }
}
