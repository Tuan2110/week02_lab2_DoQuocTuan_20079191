package com.example.week2_lab2.resources;

import com.example.week2_lab2.services.ProductImageService;
import com.example.week2_lab2.services.impl.ProductImageServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


@Path("/product-images")
public class ProductImageResources {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private ProductImageService productImageService;
    public ProductImageResources(){
        this.productImageService = new ProductImageServiceImpl();
    }
    @GET
    @Produces("application/json")
    public Response getAll(){
        return Response.ok(productImageService.getAllProductImage()).build();
    }

}
