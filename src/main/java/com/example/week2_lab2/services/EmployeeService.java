package com.example.week2_lab2.services;

import com.example.week2_lab2.enums.EmployeeStatus;
import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.Order;
import com.example.week2_lab2.response.ApiResponse;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void insertEmp(Employee employee);

    Optional<Employee> findById(long id);

    boolean delete(long id);
    boolean activeEmp(long id);
    List<Employee> getAll();
    List<Order> getOrdersByPeriod(long empId, Date from, Date to);

    ApiResponse login(String email, String password);
}
