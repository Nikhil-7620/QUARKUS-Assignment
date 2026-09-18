package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.entity.Employee;
import org.acme.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAllEmployees();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return repository.findEmployeeById(id);
    }

    @Transactional
    public Employee createEmployee(Employee employee) {
        repository.saveEmployee(employee);
        return employee;
    }

    @Transactional
    public boolean updateEmployee(Long id, Employee updatedData) {
        Optional<Employee> optionalEmp = repository.findEmployeeById(id);
        if (optionalEmp.isPresent()) {
            Employee emp = optionalEmp.get();
            emp.setName(updatedData.getName());
            emp.setEmail(updatedData.getEmail());
            emp.setDepartment(updatedData.getDepartment());
            emp.setSalary(updatedData.getSalary());
            // Panache automatically tracks and updates changes inside a transaction
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteEmployee(Long id) {
        return repository.deleteEmployee(id);
    }
}