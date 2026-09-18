package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.Employee;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    public List<Employee> findAllEmployees() {
        return listAll();
    }

    public Optional<Employee> findEmployeeById(Long id) {
        return findByIdOptional(id);
    }

    public void saveEmployee(Employee employee) {
        persist(employee);
    }

    public boolean deleteEmployee(Long id) {
        return deleteById(id);
    }

}