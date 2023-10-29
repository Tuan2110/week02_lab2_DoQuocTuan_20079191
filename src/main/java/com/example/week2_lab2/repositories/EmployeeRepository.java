package com.example.week2_lab2.repositories;

import com.example.week2_lab2.enums.EmployeeStatus;
import com.example.week2_lab2.models.Employee;
import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class EmployeeRepository {

    private SessionFactory sessionFactory;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public EmployeeRepository() {
        this.sessionFactory = MySessionFactory.getInstance().getSessionFactory();
    }
    public void insertEmp(Employee employee){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
    }
    public void setStatus(Employee employee ,EmployeeStatus status){
        employee.setStatus(status);
    }
    public void updateEmp(Employee employee){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
    }

    public Optional<Employee> findById(long id){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            transaction.commit();
            return Optional.ofNullable(employee);
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return Optional.empty();
    }
    public List<Employee> getAllEmp(){
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            List<Employee> employees = session
                    .createNamedQuery("Employee.getAllEmp",Employee.class)
                    .setParameter("status", EmployeeStatus.ACTIVE).getResultList();
            transaction.commit();
            return employees;
        }catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return null;
    }

    public Optional<Employee> finByEmailAndPassword(String email, String password) {
        Transaction transaction = null;
        Employee employee = null;
        try(Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            employee = session
                    .createNamedQuery("Employee.findByEmailAndPassword",Employee.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
            transaction.commit();
            return Optional.ofNullable(employee);
        } catch (NoResultException noResultException){
            return Optional.empty();
        } catch (Exception e){
            logger.error(e.getMessage());
            transaction.rollback();
        }
        return Optional.empty();
    }

//    public static void main(String[] args) {
//        EmployeeRepository employeeRepository = new EmployeeRepository();
//        Employee employee = employeeRepository.finByEmailAndPassword("a@gmail.co","123").get();
//        System.out.println(employee);
//    }
}
