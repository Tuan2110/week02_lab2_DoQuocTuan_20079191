package com.example.week2_lab2.repositories;

import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.ProductPrice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductPriceRepository {
    private SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public ProductPriceRepository() {
        this.sessionFactory = MySessionFactory.getInstance().getSessionFactory();
    }
    public void insert(ProductPrice productPrice){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(productPrice);
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
    }
    public List<ProductPrice> getByProductId(long id){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<ProductPrice> productPrices= session.createNamedQuery("ProductPrice.getByProductId", ProductPrice.class)
                    .setParameter("id", id)
                    .getResultList();
            transaction.commit();
            return productPrices;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }
}
