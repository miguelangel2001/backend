package com.proyecto.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String provider; // LOCAL, GOOGLE, FACEBOOK

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
}
