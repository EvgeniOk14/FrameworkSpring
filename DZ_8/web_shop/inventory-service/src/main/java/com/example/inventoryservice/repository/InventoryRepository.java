package com.example.inventoryservice.repository;

import com.example.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long>
{
    @Query("SELECT i FROM InventoryItem i WHERE i.productId = :productId")
    InventoryItem findByProductId(Long productId);
}

