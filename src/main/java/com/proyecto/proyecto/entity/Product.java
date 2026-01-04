package com.proyecto.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String description;
    private Double price;
    private Integer stock;
    private String imageUrl;
}
