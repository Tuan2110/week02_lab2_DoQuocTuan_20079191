package com.example.week2_lab2.services.impl;

import com.example.week2_lab2.models.ProductPrice;
import com.example.week2_lab2.repositories.ProductPriceRepository;
import com.example.week2_lab2.services.ProductPriceService;

import java.util.List;

public class ProductPriceServiceImpl implements ProductPriceService {
    private ProductPriceRepository productPriceRepository;
    public ProductPriceServiceImpl(){
        this.productPriceRepository = new ProductPriceRepository();
    }
    public void insertProductPrice(ProductPrice productPrice){
        productPriceRepository.insert(productPrice);
    }

    @Override
    public List<ProductPrice> getByProductId(long id) {
        return productPriceRepository.getByProductId(id);
    }
}
