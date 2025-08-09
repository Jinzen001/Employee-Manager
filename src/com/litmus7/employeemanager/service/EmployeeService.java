package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();

    public boolean createEmployee(Employee employee) throws SQLException {
        
        return dao.createEmployee(employee) > 0;
    }

    public List<Employee> getAllEmployees() throws SQLException {
        return dao.getAllEmployees();
    }

    public boolean deleteEmployee(int id) throws SQLException {
        return dao.deleteEmployee(id) > 0;
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        return dao.updateEmployee(employee) > 0;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        return dao.getEmployeeById(id); // returns null if not found
    }
}
