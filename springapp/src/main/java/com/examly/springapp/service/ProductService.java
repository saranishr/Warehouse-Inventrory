package com.examly.springapp.service;

import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepository;
import com.examly.springapp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product addProduct(Product p) {
        return repo.save(p);
    }

    public Product updateProduct(Long id, Product p) {
        Product existing = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
        
        existing.setName(p.getName());
        existing.setCategory(p.getCategory());
        existing.setPrice(p.getPrice());
        existing.setQuantity(p.getQuantity());
        existing.setLocation(p.getLocation());

        return repo.save(existing);
    }

    public void deleteProduct(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Product not found with ID: " + id);
        }
        repo.deleteById(id);
    }
}
