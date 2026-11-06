
package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.employee.models.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Display all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Save an employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Find employee by ID
    public Optional<Employee> getEmployeeById(Integer id) {
        return employeeRepository.findById(id);
    }

    // Delete employee
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }
    // Search employees by name or department
public List<Employee> searchEmployees(String keyword) {

    return employeeRepository
            .findByNameContainingIgnoreCaseOrDepartmentContainingIgnoreCase(
                    keyword, keyword);
}
}
