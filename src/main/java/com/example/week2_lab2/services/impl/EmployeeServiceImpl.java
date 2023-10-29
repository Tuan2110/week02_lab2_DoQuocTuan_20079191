package com.example.week2_lab2.services.impl;

import com.example.week2_lab2.enums.EmployeeStatus;
import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.Order;
import com.example.week2_lab2.repositories.EmployeeRepository;
import com.example.week2_lab2.response.ApiResponse;
import com.example.week2_lab2.services.EmployeeService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(){
        this.employeeRepository = new EmployeeRepository();
    }

    public void insertEmp(Employee employee){
        employeeRepository.insertEmp(employee);
    }

    public Optional<Employee> findById(long id){
        return employeeRepository.findById(id);
    }

    public boolean delete(long id){
        Optional<Employee> op = findById(id);
        if(op.isPresent()){
            Employee employee = op.get();
            employee.setStatus(EmployeeStatus.TERMINATED);
            return true;
        }
        return false;
    }
    public boolean activeEmp(long id){
        Optional<Employee> op = findById(id);
        if(op.isPresent()){
            Employee employee = op.get();
            employee.setStatus(EmployeeStatus.ACTIVE);
            return true;
        }
        return false;
    }
    public List<Employee> getAll(){
        return employeeRepository.getAllEmp();
    }
    public List<Order> getOrdersByPeriod(long empId, Date from, Date to) {
        return null;
    }

    @Override
    public ApiResponse login(String email, String password) {
        Optional<Employee> op = employeeRepository.finByEmailAndPassword(email, password);
        if(op.isPresent())
            return new ApiResponse("Login Success",true);
        return new ApiResponse("Login Failed",false);
    }

}
