package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.Response;

import java.util.List;

public class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();

    public Response<String> createEmployee(Employee employee) {
        if (employee.getFirstName() == null || employee.getFirstName().isEmpty()) {
            return new Response<>("ERROR", "First name cannot be empty", null);
        }
        int rows = dao.createEmployee(employee);
        return rows > 0 ?
                new Response<>("SUCCESS", "Employee created successfully", null) :
                new Response<>("FAIL", "Failed to create employee", null);
    }

    public Response<List<Employee>> getAllEmployees() {
        List<Employee> employees = dao.getAllEmployees();
        if (employees.isEmpty()) {
            return new Response<>("SUCCESS", "No employees found", employees);
        }
        return new Response<>("SUCCESS", "Employees retrieved successfully", employees);
    }

    public Response<String> deleteEmployee(int id) {
        int rows = dao.deleteEmployee(id);
        return rows > 0 ?
                new Response<>("SUCCESS", "Employee deleted successfully", null) :
                new Response<>("FAIL", "Employee not found", null);
    }

    public Response<String> updateEmployee(Employee employee) {
        int rows = dao.updateEmployee(employee);
        return rows > 0 ?
                new Response<>("SUCCESS", "Employee updated successfully", null) :
                new Response<>("FAIL", "No employee found with given ID", null);
    }

    // ✅ Newly added method
    public Response<Employee> getEmployeeById(int id) {
        Employee employee = dao.getEmployeeById(id);
        if (employee == null) {
            return new Response<>("FAIL", "No employee found with given ID", null);
        }
        return new Response<>("SUCCESS", "Employee retrieved successfully", employee);
    }
}
