package com.example.week2_lab2.services.impl;

import com.example.week2_lab2.models.Customer;
import com.example.week2_lab2.repositories.CustomerRepository;
import com.example.week2_lab2.response.ApiResponse;
import com.example.week2_lab2.services.CustomerService;

import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    public CustomerServiceImpl(){
        this.customerRepository = new CustomerRepository();
    }
    @Override
    public ApiResponse login(String email, String password) {
        Optional<Customer> op = customerRepository.findByEmailAndPassword(email, password);
        if(op.isPresent())
            return new ApiResponse(op.get().getId()+"",true);
        return new ApiResponse("Login Failed",false);
    }
}
