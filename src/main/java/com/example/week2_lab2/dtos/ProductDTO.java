package com.example.week2_lab2.dtos;

import com.example.week2_lab2.enums.ProductStatus;
import com.example.week2_lab2.models.OrderDetail;
import com.example.week2_lab2.models.ProductImage;
import com.example.week2_lab2.models.ProductPrice;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class ProductDTO {
    private long id;
    private String name;
    private String description;
    private String unit;
    private String manufacturer;
    private ProductStatus status;
    private List<ProductImage> productImages;
    private double price;

    public ProductDTO() {
    }

    public ProductDTO(long id, String name, String description, String unit, String manufacturer, ProductStatus status, List<ProductImage> productImages, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufacturer = manufacturer;
        this.status = status;
        this.productImages = productImages;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
