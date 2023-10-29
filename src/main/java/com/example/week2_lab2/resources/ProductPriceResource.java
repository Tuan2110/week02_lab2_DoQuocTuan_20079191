package com.example.week2_lab2.resources;

import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.models.ProductPrice;
import com.example.week2_lab2.services.ProductPriceService;
import com.example.week2_lab2.services.impl.ProductPriceServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/product-price")
public class ProductPriceResource {
    private final ProductPriceService productPriceService;
    public ProductPriceResource(){
        this.productPriceService = new ProductPriceServiceImpl();
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(ProductPrice productPrice) {
        productPriceService.insertProductPrice(productPrice);
        return Response.ok(productPrice).build();
    }
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getByProductId(@PathParam("id") long id) {
        return Response.ok(productPriceService.getByProductId(id)).build();
    }
}
