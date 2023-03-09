package com.example.elasticSearchExampleProject.controllers;

import com.example.elasticSearchExampleProject.service.ProductService;
import com.example.elasticSearchExampleProject.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam("name") String name) {
        List<Product> products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping(params = { "name", "page", "size" })
    public ResponseEntity<Page<Product>> getProductsByNameWithPagination(@RequestParam("name") String name,
                                                                         @RequestParam("page") Integer page,
                                                                         @RequestParam("size") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.getProductsByNameWithPagination(name, pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping(params = { "minPrice", "maxPrice" })
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam("minPrice") Double minPrice,
                                                                 @RequestParam("maxPrice") Double maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping(params = { "startDate", "endDate" })
    public ResponseEntity<List<Product>> getProductsByCreatedDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Product> products = productService.getProductsByCreatedDateRange(startDate, endDate);
        return ResponseEntity.ok(products);
    }

    @GetMapping(params = "searchName")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam("searchName") String name) {
        List<Product> products = productService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping(params = "searchDescription")
    public ResponseEntity<List<Product>> searchProductsByDescription(@RequestParam("searchDescription") String description) {
        List<Product> products = productService.searchProductsByDescription(description);
        return ResponseEntity.ok(products);
    }

    @GetMapping(params = { "searchName", "minPrice", "maxPrice" })
    public ResponseEntity<List<Product>> searchProductsByCombinedQuery(@RequestParam("searchName") String name,
                                                                       @RequestParam("minPrice") Double minPrice,
                                                                       @RequestParam("maxPrice") Double maxPrice) {
        List<Product> products = productService.searchProductsByCombinedQuery(name, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

    @GetMapping(params = "sortBy")
    public ResponseEntity<List<Product>> searchProductsWithSorting(@RequestParam("sortBy") String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        List<Product> products = productService.searchProductsWithSorting(sort);
        return ResponseEntity.ok(products);
    }
}

