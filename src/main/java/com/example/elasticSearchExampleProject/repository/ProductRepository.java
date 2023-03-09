package com.example.elasticSearchExampleProject.repository;

import com.example.elasticSearchExampleProject.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;
import java.util.List;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByName(String name);

    Page<Product> findByName(String name, Pageable pageable);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByCreatedDateBetween(Date startDate, Date endDate);

    @Query("{\"match\": {\"name\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}")
    List<Product> searchByName(String name);

    @Query("{\"match\": {\"description\": {\"query\": \"?0\"}}}")
    List<Product> searchByDescription(String description);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": {\"query\": \"?0\", \"fuzziness\": \"AUTO\"}}}, {\"range\": {\"price\": {\"gte\": ?1, \"lte\": ?2}}}]}}")
    List<Product> searchByCombinedQuery(String name, Double minPrice, Double maxPrice);

    @Query("{\"match_all\": {}}")
    List<Product> searchWithSorting(Sort sort);
}

