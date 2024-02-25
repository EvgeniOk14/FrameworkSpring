package com.example.inventorymanagementservice.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "table_product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class WarehouseItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;
}

