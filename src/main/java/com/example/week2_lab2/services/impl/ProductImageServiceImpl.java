package com.example.week2_lab2.services.impl;

import com.example.week2_lab2.models.ProductImage;
import com.example.week2_lab2.repositories.ProductImageRepository;
import com.example.week2_lab2.services.ProductImageService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ProductImageServiceImpl implements ProductImageService {
    private final Path storageFolder = Paths.get("uploads");

    private ProductImageRepository productImageRepository;
    public ProductImageServiceImpl(){
        this.productImageRepository = new ProductImageRepository();
    }
    public List<ProductImage> getAllProductImage(){
        return productImageRepository.getAllProductImage();
    }
}
