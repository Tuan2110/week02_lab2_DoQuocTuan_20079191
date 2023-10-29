package com.example.week2_lab2.resources;

import com.example.week2_lab2.models.OrderDetail;
import com.example.week2_lab2.services.OrderDetailService;
import com.example.week2_lab2.services.impl.OrderDetailServiceImpl;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/order-details")
public class OrderDetailResources {
    private OrderDetailService orderDetailService;
    public OrderDetailResources(){
        this.orderDetailService = new OrderDetailServiceImpl();
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insertMany(List<OrderDetail> orderDetails){
        orderDetailService.insertMany(orderDetails);
        return Response.ok(orderDetails).build();
    }
}
