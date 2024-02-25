package com.example.microservicesaspectshop.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Component
@Entity
public class Product
{

    private Long id;


    private String name;


    private double price;


    private String description;


    private int quantity;
}
