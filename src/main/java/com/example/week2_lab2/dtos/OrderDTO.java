package com.example.week2_lab2.dtos;

import com.example.week2_lab2.models.OrderDetail;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private long id;
    private LocalDate orderDate;
    private String address;
    private long customerId;
    private String customerName;
    private long employeeId;
    private String employeeName;
    private int totalQuantity;
    private double totalPrice;
    private List<OrderDetail> orderDetails;
    public OrderDTO() {
    }

    public OrderDTO(long id, LocalDate orderDate, String address, long customerId, String customerName, long employeeId, String employeeName, int totalQuantity, double totalPrice, List<OrderDetail> orderDetails) {
        this.id = id;
        this.orderDate = orderDate;
        this.address = address;
        this.customerId = customerId;
        this.customerName = customerName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.orderDetails = orderDetails;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
