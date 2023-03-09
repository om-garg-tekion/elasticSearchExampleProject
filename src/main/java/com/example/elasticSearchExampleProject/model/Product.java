package com.example.elasticSearchExampleProject.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Data
@Document(indexName = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private Double price;
    private Date createdDate;
}
