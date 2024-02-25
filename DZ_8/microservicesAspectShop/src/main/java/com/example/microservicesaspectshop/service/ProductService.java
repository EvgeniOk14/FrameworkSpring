package com.example.microservicesaspectshop.services;

import com.example.microservicesaspectshop.models.Product;
import com.example.microservicesaspectshop.repository.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Component
@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProdact()
    {
       return productRepository.findAll();
    }

    public Product gitProductById(Long id)
    {
        Product findProduct = productRepository.findById(id).stream().findAny().orElse(null);
        return findProduct;
    }
    public Product createNewProduct(Product product)
    {
        return productRepository.save(product);
    }

    @Transactional
    public void reserveProduct(Long productId, int quantity)
    {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        int availableQuantity = product.getQuantity();
        if (availableQuantity < quantity)
        {
            throw new IllegalArgumentException("Not enough quantity available");
        }

        product.setQuantity(availableQuantity - quantity);
        productRepository.save(product);
    }

}
