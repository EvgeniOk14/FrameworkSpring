package com.example.inventoryservice.controller;

import com.example.inventoryservice.exception.InsufficientStockException;
import com.example.inventoryservice.model.InventoryItem;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryReservationController
{
    @Autowired
    private final InventoryService inventoryService;


    public InventoryReservationController(InventoryService inventoryService)
    {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/all")
    public void showAll()
    {
        inventoryService.showAll();
    }

    @PostMapping("/reserve/{productId}")
    public void reserveProduct(@PathVariable Long productId, @RequestParam int quantity)
    {
        inventoryService.reserveProduct(productId, quantity);
    }

}

