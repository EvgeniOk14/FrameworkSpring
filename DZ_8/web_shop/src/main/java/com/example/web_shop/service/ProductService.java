package com.example.web_shop.service;

import com.example.web_shop.exceptions.ProductNotFoundException;
import com.example.web_shop.models.Product;
import com.example.web_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;



    public ProductService(ProductRepository productRepository, RestTemplate restTemplate)
    {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public void reserveProduct(Long id, int availableQuantity)
    {
        String inventoryServiceUrl = "http://inventory-service/api/inventory/reserve?id={id}&availableQuantity={availableQuantity}";
        restTemplate.postForObject(inventoryServiceUrl, null, Void.class, id, availableQuantity );
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Product getProductById(Long id)
    {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    public void addProduct(Product product)
    {
        productRepository.save(product);
    }

    public void updateProduct(Long id, Product updatedProduct)
    {
        Product product = getProductById(id);
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        productRepository.save(product);
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }

}




