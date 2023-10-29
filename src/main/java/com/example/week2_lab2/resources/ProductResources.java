package com.example.week2_lab2.resources;

import com.example.week2_lab2.services.ProductService;
import com.example.week2_lab2.services.impl.ProductServiceIplm;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/products")
public class ProductResources {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private ProductService productService;
    public ProductResources(){
        this.productService = new ProductServiceIplm();
    }
    @GET
    @Produces("application/json")
    public Response getAll(){
        return Response.ok(productService.getAllProduct()).build();
    }
}
