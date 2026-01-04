package com.proyecto.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name; // SUPER_ADMIN, ADMIN, USER
}
