package com.example.week2_lab2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order.findAll",query = "from Order"),
        @NamedQuery(name = "Order.getOrdersByPeriod",query = "from Order o where o.orderDate between :fromDate and :toDate"),
        @NamedQuery(name = "Order.getOrdersByDate" , query = "from Order o where o.orderDate = :date"),
        @NamedQuery(name = "Order.getOrdersByEmpPeriod",query = "from Order o where o.employee.id = :empId and o.orderDate between :fromDate and :toDate")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id",length = 20)
    private long id;
    @Column(name = "order_date",nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate orderDate;
    @ManyToOne
    @JoinColumn(name = "cust_id",referencedColumnName = "cust_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "emp_id")
    private Employee employee;
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails = new ArrayList<>();
    private String address;

    public Order() {
    }
    public Order(long id) {
        this.id = id;
    }
    public Order(long id, LocalDate orderDate, Customer customer, Employee employee,String address) {
        this.id = id;
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.address = address;
    }

    public Order(LocalDate orderDate, Customer customer, Employee employee,String address) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.address = address;
    }

    public Order(LocalDate orderDate, Customer customer, Employee employee, List<OrderDetail> orderDetails, String address) {
        this.orderDate = orderDate;
        this.customer = customer;
        this.employee = employee;
        this.orderDetails = orderDetails;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", customer=" + customer +
                ", employee=" + employee +
                '}';
    }
}
