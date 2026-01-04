package com.proyecto.proyecto.controller;

import com.proyecto.proyecto.dto.OrderItemRequest;
import com.proyecto.proyecto.entity.OrderItem;
import com.proyecto.proyecto.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    /**
     * Agregar item a una orden
     */
    @PostMapping("/{orderId}")
    public ResponseEntity<OrderItem> addItem(
            @PathVariable Long orderId,
            @Valid @RequestBody OrderItemRequest request,
            Authentication authentication) {

        return ResponseEntity.ok(
                orderItemService.addItem(orderId, request, authentication.getName())
        );
    }

    /**
     * Listar items por orden
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<List<OrderItem>> getItemsByOrder(
            @PathVariable Long orderId,
            Authentication authentication) {

        return ResponseEntity.ok(
                orderItemService.getItemsByOrder(orderId, authentication.getName())
        );
    }

    /**
     * Eliminar item de una orden
     */
    @DeleteMapping("/{itemId}")
    public ResponseEntity<?> deleteItem(
            @PathVariable Long itemId,
            Authentication authentication) {

        orderItemService.deleteItem(itemId, authentication.getName());
        return ResponseEntity.ok().build();
    }
}
