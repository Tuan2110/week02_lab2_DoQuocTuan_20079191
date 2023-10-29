package com.example.week2_lab2.repositories;

import com.example.week2_lab2.models.Customer;
import com.example.week2_lab2.models.Employee;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CustomerRepository {
    private SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public CustomerRepository() {
        this.sessionFactory = MySessionFactory.getInstance().getSessionFactory();
    }
    public Optional<Customer> findByEmailAndPassword(String email, String password){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Customer customer = session
                    .createNamedQuery("Customer.findByEmailAndPassword", Customer.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            transaction.commit();
            return Optional.ofNullable(customer);
        } catch (NoResultException noResultException){
            return Optional.empty();
        } catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return Optional.empty();
    }
}
