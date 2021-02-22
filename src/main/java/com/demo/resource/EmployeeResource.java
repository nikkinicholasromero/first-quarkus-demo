package com.demo.resource;

import com.demo.model.Employee;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Path("/employee")
public class EmployeeResource {
    private final List<Employee> employees;

    public EmployeeResource() {
        Employee employee0 = new Employee();
        employee0.setId(1);
        employee0.setFirstName("Nikki Nicholas");
        employee0.setLastName("Romero");
        employee0.setBirthDate(LocalDate.of(1991, Month.AUGUST, 5));
        employee0.setSalary(new BigDecimal("22750.75"));
        employee0.setActive(false);
        employee0.setCreatedTimestamp(LocalDateTime.now());

        Employee employee1 = new Employee();
        employee1.setId(2);
        employee1.setFirstName("Leslie Anne");
        employee1.setLastName("Sayin");
        employee1.setBirthDate(LocalDate.of(1992, Month.AUGUST, 21));
        employee1.setSalary(new BigDecimal("32750.25"));
        employee1.setActive(true);
        employee1.setCreatedTimestamp(LocalDateTime.now());

        employees = Arrays.asList(employee0, employee1);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee findById(@PathParam("id") long id) {
        return employees.stream().filter(e -> e.getId() == id).findFirst().orElse(new Employee());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> findByActive(@QueryParam("active") boolean active) {
        return employees.stream().filter(Employee::isActive).collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response upsert(@HeaderParam("user-agent") String userAgent, Employee employee) {
        return Response.accepted()
                .header("x-header", "x-value")
                .build();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) {
    }
}
