package com.examly.springapp.controller;

import com.examly.springapp.model.InventoryTransaction;
import com.examly.springapp.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    // Get all inventory transactions
    @GetMapping("/transactions")
    public List<InventoryTransaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    // Adjust stock (IN or OUT)
    @PostMapping("/adjust")
    public InventoryTransaction adjustStock(@RequestBody Map<String, String> request) {
        Long productId = Long.parseLong(request.get("productId"));
        String type = request.get("transactionType");
        int quantity = Integer.parseInt(request.get("quantity"));

        return service.adjustStock(productId, type, quantity);
    }
}
