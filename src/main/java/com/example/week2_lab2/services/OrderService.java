package com.example.week2_lab2.services;

import com.example.week2_lab2.dtos.OrderDTO;
import com.example.week2_lab2.models.Order;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<OrderDTO> findById(long id);
    void insert(Order order);
    List<OrderDTO> getAll();
    List<OrderDTO> getOrdersByPeriod(Date fromDate, Date toDate);
    List<OrderDTO> getOrdersByDate(Date date);
    List<OrderDTO> getOrdersByEmpPeriod(long empId, Date fromDate, Date toDate);
}
