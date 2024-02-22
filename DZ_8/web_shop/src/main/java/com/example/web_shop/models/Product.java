package com.example.web_shop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "table_products")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "availableQuantity")
    private int availableQuantity;
}
