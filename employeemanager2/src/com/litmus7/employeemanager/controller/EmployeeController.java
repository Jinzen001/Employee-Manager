package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.Response;

import java.util.List;

public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    public Response<String> createEmployee(Employee employee) {
        return employeeService.createEmployee(employee);
    }

    public Response<List<Employee>> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    public Response<String> updateEmployee(Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    public Response<String> deleteEmployee(int empId) {
        return employeeService.deleteEmployee(empId);
    }

    public Response<Employee> getEmployeeById(int empId) {
        return employeeService.getEmployeeById(empId);
    }
}
