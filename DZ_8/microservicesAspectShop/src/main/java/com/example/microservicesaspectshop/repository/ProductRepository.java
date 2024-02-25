package com.example.microservicesaspectshop.repository;

import com.example.microservicesaspectshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{

}

