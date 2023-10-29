package com.example.week2_lab2.services;

import com.example.week2_lab2.models.OrderDetail;
import com.example.week2_lab2.repositories.OrderDetailRepository;

import java.util.List;

public interface OrderDetailService {
    void insertMany(List<OrderDetail> orderDetails);
}

