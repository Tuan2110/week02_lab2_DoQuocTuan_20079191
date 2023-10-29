package com.example.week2_lab2.services.impl;

import com.example.week2_lab2.dtos.ProductDTO;
import com.example.week2_lab2.models.Product;
import com.example.week2_lab2.models.ProductPrice;
import com.example.week2_lab2.repositories.ProductRepository;
import com.example.week2_lab2.services.ProductService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ProductServiceIplm implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceIplm(){
        this.productRepository = new ProductRepository();
    }
    public List<ProductDTO> getAllProduct(){
        List<Product> products = productRepository.getAllProduct();
        return products.stream().map(this::convertProduct).toList();
    }
    private ProductDTO convertProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setUnit(product.getUnit());
        productDTO.setManufacturer(product.getManufacturer());
        productDTO.setStatus(product.getStatus());
        productDTO.setProductImages(product.getProductImages());
        LocalDate closestDate = null;
        long closestDifference = Long.MAX_VALUE;
        for (ProductPrice productPrice : product.getProductPrices()) {
            long difference = Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), productPrice.getPriceDateTime()));
            if (difference < closestDifference) {
                closestDate = productPrice.getPriceDateTime();
                closestDifference = difference;
            }
        }
        LocalDate finalClosestDate = closestDate;
        productDTO.setPrice(product.getProductPrices().stream().filter(productPrice -> productPrice.getPriceDateTime().equals(finalClosestDate)).findFirst().get().getPrice());
        return productDTO;
    }
}
