package com.examly.springapp.service;

import com.examly.springapp.model.InventoryTransaction;
import com.examly.springapp.model.Product;
import com.examly.springapp.repository.InventoryTransactionRepository;
import com.examly.springapp.repository.ProductRepository;
import com.examly.springapp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryTransactionRepository txnRepo;
    private final ProductRepository productRepo;

    public InventoryService(InventoryTransactionRepository txnRepo, ProductRepository productRepo) {
        this.txnRepo = txnRepo;
        this.productRepo = productRepo;
    }

    public List<InventoryTransaction> getAllTransactions() {
        return txnRepo.findAll();
    }

    public InventoryTransaction adjustStock(Long productId, String type, int qty) {
        Product product = productRepo.findById(productId)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + productId));

        if ("OUT".equalsIgnoreCase(type) && product.getQuantity() < qty) {
            throw new IllegalArgumentException("Insufficient stock to perform transaction.");
        }

        int newQty = "IN".equalsIgnoreCase(type) ? product.getQuantity() + qty : product.getQuantity() - qty;
        product.setQuantity(newQty);
        productRepo.save(product);

        InventoryTransaction txn = new InventoryTransaction();
        txn.setProduct(product);
        txn.setTransactionType(type);
        txn.setQuantity(qty);
        txn.setDate(new Date());

        return txnRepo.save(txn);
    }
}
