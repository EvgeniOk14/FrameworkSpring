package com.example.inventoryservice.service;

import com.example.inventoryservice.exception.InsufficientStockException;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.repository.InventoryRepository;
import com.example.web_shop.exceptions.ProductNotFoundException;
import com.example.web_shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryService
{
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    public void reserveProduct(Long productId, int quantity)
    {
        // Запрос на получение информации о товаре из web_shop
        //ResponseEntity<Product> response = restTemplate.getForEntity("http://web_shop/api/products/{id}", Product.class, productId);
        ResponseEntity<Product> response = restTemplate.getForEntity("http://localhost:8080/api/products/{id}", Product.class, productId);

        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null)
        {
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }

        Product product = response.getBody();
        if (product.getAvailableQuantity() < quantity)
        {
            throw new InsufficientStockException("Insufficient stock for product with ID: " + productId);
        }

        // Отправка запроса на резервирование товара в inventory-service
        //String reserveUrl = "http://inventory-service/api/inventory/reserve/{productId}?quantity={quantity}";
        String reserveUrl = "http://localhost:8081/api/inventory/reserve/{productId}?quantity={quantity}";
        restTemplate.postForObject(reserveUrl, null, Void.class, productId, quantity);
    }
    public void showAll()
    {
        inventoryRepository.findAll();
    }
}


//    public void reserveProduct(Long productId, int quantity)
//    {
//        InventoryItem item = inventoryRepository.findByProductId(productId);
//        if (item == null || item.getQuantity() < quantity)
//        {
//            throw new InsufficientStockException("Insufficient stock for product with ID: " + productId);
//        }
//        item.setQuantity(item.getQuantity() - quantity);
//        inventoryRepository.save(item);
//    }


//@Service
//public class InventoryService
//{
//    @Autowired
//    private final ProductRepository productRepository;
//
//    public InventoryService(ProductRepository productRepository)
//    {
//        this.productRepository = productRepository;
//
//    }
//
//    @Transactional
//    public void reserveProduct(Long id, int availableQuantity)
//    {
//        InventoryItem product = productRepository.findById(id)
//                .orElseThrow(() -> new InsufficientStockException("Product not found with id: " + id));
//
//        int currentAvailableQuantity = product.getAvailableQuantity();
//
//        if (currentAvailableQuantity >= availableQuantity)
//        {
//            product.setAvailableQuantity(currentAvailableQuantity - availableQuantity);
//            productRepository.save(product);
//        }
//        else
//        {
//            throw new InsufficientStockException("Insufficient stock for product with id: " + id);
//        }
//    }
//}
