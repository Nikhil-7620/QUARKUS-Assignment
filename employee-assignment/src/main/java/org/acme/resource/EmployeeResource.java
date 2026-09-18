package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.Employee;
import org.acme.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeService service;

    @GET
    public List<Employee> getAll() {
        return service.getAllEmployees();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Optional<Employee> employee = service.getEmployeeById(id);
        if (employee.isPresent()) {
            return Response.ok(employee.get()).build(); // 200 OK
        }
        return Response.status(Response.Status.NOT_FOUND).build(); // 404 Not Found
    }

    @POST
    public Response create(@Valid Employee employee) {
        Employee created = service.createEmployee(employee);
        return Response.status(Response.Status.CREATED).entity(created).build(); // 201 Created
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid Employee employee) {
        boolean updated = service.updateEmployee(id, employee);
        if (updated) {
            return Response.ok(service.getEmployeeById(id).get()).build(); // 200 OK
        }
        return Response.status(Response.Status.NOT_FOUND).build(); // 404 Not Found
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = service.deleteEmployee(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build(); // 204 No Content
        }
        return Response.status(Response.Status.NOT_FOUND).build(); // 404 Not Found
    }
}