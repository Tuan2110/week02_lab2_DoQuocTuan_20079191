package com.example.week2_lab2.repositories;

import com.example.week2_lab2.dtos.OrderDTO;
import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.Order;
import com.example.week2_lab2.models.OrderDetail;
import com.example.week2_lab2.services.impl.OrderServiceImpl;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public OrderRepository() {
        this.sessionFactory = MySessionFactory.getInstance().getSessionFactory();
    }
    public Optional<Order> findById(long id){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            transaction.commit();
            return Optional.ofNullable(order);
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return Optional.empty();
    }
    public List<Order> findAll(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Order> orders = session.createNamedQuery("Order.findAll", Order.class).getResultList();
            transaction.commit();
            return orders;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }
    public List<Order> getOrdersByPeriod(LocalDate fromDate, LocalDate toDate){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Order> orders = session.createNamedQuery("Order.getOrdersByPeriod", Order.class)
                    .setParameter("fromDate",fromDate)
                    .setParameter("toDate",toDate)
                    .getResultList();
            transaction.commit();
            return orders;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }
    public List<Order> getOrdersByDate(LocalDate date){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Order> orders = session.createNamedQuery("Order.getOrdersByDate", Order.class)
                    .setParameter("date",date)
                    .getResultList();
            transaction.commit();
            return orders;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }
    public List<Order> getOrdersByEmpPeriod(long empId,LocalDate fromDate, LocalDate toDate){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Order> orders = session.createNamedQuery("Order.getOrdersByEmpPeriod", Order.class)
                    .setParameter("empId",empId)
                    .setParameter("fromDate",fromDate)
                    .setParameter("toDate",toDate)
                    .getResultList();
            transaction.commit();
            return orders;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }
    public void insert(Order order){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(order);
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
    }
    public void insertOrderAndDetails(Order order){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(order);
            Long orderID = order.getId();
            for(OrderDetail detail : order.getOrderDetails()) {
                Order orderInsert = new Order();
                orderInsert.setId(orderID);
                detail.setOrder(orderInsert);
                session.persist(detail);
            }
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
