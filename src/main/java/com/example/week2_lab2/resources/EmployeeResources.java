package com.example.week2_lab2.resources;

import com.example.week2_lab2.models.Employee;
import com.example.week2_lab2.services.EmployeeService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Path("/employees")
public class EmployeeResources {
    private final EmployeeService employeeService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    public EmployeeResources(){
        this.employeeService = new EmployeeService();
    }
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getEmployeeById(@PathParam("id") long id){
        Optional<Employee> op = employeeService.findById(id);
        if(op.isPresent()){
            return Response.ok(op.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Produces("application/json")
    public Response getAll(){
        return Response.ok(employeeService.getAll()).build();
    }
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response insert(Employee employee){
        employeeService.insertEmp(employee);
        return Response.ok(employee).build();
    }
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id){
        if(employeeService.delete(id)){
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
