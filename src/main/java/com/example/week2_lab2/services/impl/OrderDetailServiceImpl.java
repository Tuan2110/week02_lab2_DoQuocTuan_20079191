package com.example.week2_lab2.services.impl;

import com.example.week2_lab2.models.OrderDetail;
import com.example.week2_lab2.repositories.OrderDetailRepository;
import com.example.week2_lab2.repositories.OrderRepository;
import com.example.week2_lab2.services.OrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    public OrderDetailServiceImpl(){
        this.orderDetailRepository = new OrderDetailRepository();
    }
    @Override
    public void insertMany(List<OrderDetail> orderDetails) {
        orderDetailRepository.insertMany(orderDetails);
    }
}
