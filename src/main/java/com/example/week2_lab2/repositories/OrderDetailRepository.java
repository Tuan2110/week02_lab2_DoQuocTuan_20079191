package com.example.week2_lab2.repositories;

import com.example.week2_lab2.models.Order;
import com.example.week2_lab2.models.OrderDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class OrderDetailRepository {
    private SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public OrderDetailRepository() {
        this.sessionFactory = MySessionFactory.getInstance().getSessionFactory();
    }

    public void insertMany(List<OrderDetail> orderDetails){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            for(OrderDetail orderDetail : orderDetails) {
                session.persist(orderDetail);
            }
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
    }
}
