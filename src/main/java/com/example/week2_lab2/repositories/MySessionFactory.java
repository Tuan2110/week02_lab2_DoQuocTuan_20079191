package com.example.week2_lab2.repositories;

import com.example.week2_lab2.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MySessionFactory {
    private static MySessionFactory instance = null;
    private SessionFactory sessionFactory = null;
    public MySessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductPrice.class)
                .addAnnotatedClass(ProductImage.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderDetail.class)
                .getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static MySessionFactory getInstance() {
        if (instance == null)
            instance = new MySessionFactory();
        return instance;
    }
}
