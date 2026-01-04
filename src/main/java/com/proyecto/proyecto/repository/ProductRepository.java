package com.proyecto.proyecto.repository;

import com.proyecto.proyecto.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
