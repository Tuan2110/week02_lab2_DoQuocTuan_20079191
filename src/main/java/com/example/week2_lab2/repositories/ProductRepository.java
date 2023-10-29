package com.example.week2_lab2.repositories;

import com.example.week2_lab2.enums.EmployeeStatus;
import com.example.week2_lab2.enums.ProductStatus;
import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductRepository {
    private SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public ProductRepository() {
        this.sessionFactory = MySessionFactory.getInstance().getSessionFactory();
    }

    public List<Product> getAllProduct(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Product> products = session
                    .createNamedQuery("Product.getAllProduct",Product.class)
                    .setParameter("status", ProductStatus.ACTIVE).getResultList();
            transaction.commit();
            return products;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }
}
