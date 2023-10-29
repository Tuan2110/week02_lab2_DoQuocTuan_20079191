package com.example.week2_lab2.resources;

import com.example.week2_lab2.dtos.OrderDTO;
import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.Order;
import com.example.week2_lab2.services.OrderService;
import com.example.week2_lab2.services.impl.OrderServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Path("/orders")
public class OrderResources {
    private final OrderService orderService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public OrderResources() {
        this.orderService = new OrderServiceImpl();
    }
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Optional<OrderDTO> op = orderService.findById(id);
        if (op.isPresent()) {
            return Response.ok(op.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/period")
    public Response getOrdersByPeriod(@QueryParam("fromDate") Date fromDate, @QueryParam("toDate") Date toDate) {
        List<OrderDTO> op = orderService.getOrdersByPeriod(fromDate, toDate);
        if (op !=null) {
            return Response.ok(op).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/date")
    public Response getOrderByDate(@QueryParam("date") Date date) {
        List<OrderDTO> op = orderService.getOrdersByDate(date);
        if (op !=null) {
            return Response.ok(op).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/empPeriod")
    public Response getOrdersByPeriod(@QueryParam("empId")long empId,@QueryParam("fromDate") Date fromDate, @QueryParam("toDate") Date toDate) {
        List<OrderDTO> op = orderService.getOrdersByEmpPeriod(empId,fromDate, toDate);
        if (op !=null) {
            return Response.ok(op).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces("application/json")
    public Response getAll() {
        List<OrderDTO> op = orderService.getAll();
        if (op !=null) {
            return Response.ok(op).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(Order order) {
        orderService.insert(order);
        return Response.ok(order).build();
    }
}
