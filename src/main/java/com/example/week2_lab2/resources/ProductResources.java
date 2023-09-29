package com.example.week2_lab2.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/products")
public class ProductResources {
    @GET
    public Response getAll(){
        return Response.ok("Hello").build();
    }
}
