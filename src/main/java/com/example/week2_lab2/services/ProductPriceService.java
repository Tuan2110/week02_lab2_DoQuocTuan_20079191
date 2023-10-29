package com.example.week2_lab2.services;

import com.example.week2_lab2.models.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    void insertProductPrice(ProductPrice productPrice);
    List<ProductPrice> getByProductId(long id);
}
