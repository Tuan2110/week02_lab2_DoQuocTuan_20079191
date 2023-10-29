package com.example.week2_lab2.resources;

import com.example.week2_lab2.response.ApiResponse;
import com.example.week2_lab2.services.CustomerService;
import com.example.week2_lab2.services.impl.CustomerServiceImpl;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/customers")
public class CustomerResources {
    private final CustomerService customerService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public CustomerResources() {
        this.customerService = new CustomerServiceImpl();
    }
    @POST
    @Produces("application/json")
    @Path("/login")
    public Response login(@QueryParam("email") String email, @QueryParam("password") String password) {
        ApiResponse api = customerService.login(email, password);
        return Response.ok(api).build();
    }

}
