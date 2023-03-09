package com.example.elasticSearchExampleProject.service;

import com.example.elasticSearchExampleProject.model.Product;
import com.example.elasticSearchExampleProject.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    public Page<Product> getProductsByNameWithPagination(String name, Pageable pageable) {
        return productRepository.findByName(name, pageable);
    }

    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsByCreatedDateRange(Date startDate, Date endDate) {
        return productRepository.findByCreatedDateBetween(startDate, endDate);
    }

    public List<Product> searchProductsByName(String name) {
        return productRepository.searchByName(name);
    }

    public List<Product> searchProductsByDescription(String description) {
        return productRepository.searchByDescription(description);
    }

    public List<Product> searchProductsByCombinedQuery(String name, Double minPrice, Double maxPrice) {
        return productRepository.searchByCombinedQuery(name, minPrice, maxPrice);
    }

    public List<Product> searchProductsWithSorting(Sort sort) {
        return productRepository.searchWithSorting(sort);
    }
}

