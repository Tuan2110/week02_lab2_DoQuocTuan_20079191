package com.example.week2_lab2.services.impl;

import com.example.week2_lab2.dtos.OrderDTO;
import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.Order;
import com.example.week2_lab2.models.OrderDetail;
import com.example.week2_lab2.repositories.OrderRepository;
import com.example.week2_lab2.services.OrderService;
import jakarta.ejb.Local;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    public OrderServiceImpl(){
        this.orderRepository = new OrderRepository();
    }
    @Override
    public Optional<OrderDTO> findById(long id) {
        Optional<Order> op = orderRepository.findById(id);
        return op.map(this::convertOrder);
    }
    private OrderDTO convertOrder(Order order){
        int totalQuanity = 0;
        double totalPrice = 0;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setCustomerId(order.getCustomer().getId());
        orderDTO.setCustomerName(order.getCustomer().getName());
        orderDTO.setEmployeeId(order.getEmployee().getId());
        orderDTO.setEmployeeName(order.getEmployee().getFullName());
        orderDTO.setOrderDetails(order.getOrderDetails());
        for(OrderDetail od : order.getOrderDetails()){
            totalQuanity += od.getQuantity();
            totalPrice += od.getQuantity() * od.getPrice();
        }
        orderDTO.setTotalQuantity(totalQuanity);
        orderDTO.setTotalPrice(totalPrice);
        return orderDTO;
    }
    @Override
    public void insert(Order order) {
        orderRepository.insertOrderAndDetails(order);
    }

    @Override
    public List<OrderDTO> getAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(this::convertOrder).toList();
    }
    @Override
    public List<OrderDTO> getOrdersByPeriod(Date fromDate, Date toDate) {
        LocalDate from = Date.valueOf(fromDate.toString()).toLocalDate();
        LocalDate to = Date.valueOf(toDate.toString()).toLocalDate();
        List<Order> orders = orderRepository.getOrdersByPeriod(from,to);
        return orders.stream().map(this::convertOrder).toList();
    }

    @Override
    public List<OrderDTO> getOrdersByDate(Date date) {
        LocalDate d = Date.valueOf(date.toString()).toLocalDate();
        List<Order> orders = orderRepository.getOrdersByDate(d);
        return orders.stream().map(this::convertOrder).toList();
    }

    @Override
    public List<OrderDTO> getOrdersByEmpPeriod(long empId, Date fromDate, Date toDate) {
        LocalDate from = Date.valueOf(fromDate.toString()).toLocalDate();
        LocalDate to = Date.valueOf(toDate.toString()).toLocalDate();
        List<Order> orders = orderRepository.getOrdersByEmpPeriod(empId,from,to);
        return orders.stream().map(this::convertOrder).toList();
    }

}
