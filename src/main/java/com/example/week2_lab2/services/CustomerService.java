package com.example.week2_lab2.services;

import com.example.week2_lab2.response.ApiResponse;

public interface CustomerService {
    ApiResponse login(String email, String password);
}
