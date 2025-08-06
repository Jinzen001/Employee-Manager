package com.litmus7.employeemanager.app;


import java.util.List;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.employee.Employee;

public class EmployeeManager {
    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();

        // 1. Insert employee
        dao.createEmployee(new Employee(0, "John", "Doe", "john.doe@example.com", 50000));

        // 2. Get all employees
        List<Employee> employees = dao.getAllEmployees();
        

        // 3. Get employee by ID
        Employee emp = dao.getEmployeeById(1);
        System.out.println("Fetched Employee: " + emp);

        // 4. Update employee
        if (emp != null) {
            emp.setSalary(60000);
            dao.updateEmployee(emp);
        }

        // 5. Delete employee
        dao.deleteEmployee(1);
    }
}
