package com.example.week2_lab2.repositories;

import com.example.week2_lab2.enums.ProductStatus;
import com.example.week2_lab2.models.Product;
import com.example.week2_lab2.models.ProductImage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ProductImageRepository {
    private SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public ProductImageRepository() {
        this.sessionFactory = MySessionFactory.getInstance().getSessionFactory();
    }

    public List<ProductImage> getAllProductImage(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<ProductImage> productImages = session
                    .createNamedQuery("ProductImage.getAllProductImage", ProductImage.class).getResultList();
            transaction.commit();
            return productImages;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }
}
