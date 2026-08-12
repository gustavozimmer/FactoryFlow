package com.zimmer.FactoryFlow.entity;


import jakarta.persistence.*;

@Entity
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

}
