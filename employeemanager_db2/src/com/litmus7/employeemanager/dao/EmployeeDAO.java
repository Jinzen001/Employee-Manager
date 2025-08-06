package com.litmus7.employeemanager.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.litmus7.employeemanager.employee.Employee;

public class EmployeeDAO {
    private final String url = "jdbc:mysql://localhost:3306/empdb";
    private final String user = "root";
    private final String pass = "jinzen@2001";

    public EmployeeDAO() {
        
    }

    // 1. Create Employee
    public void createEmployee(Employee emp) {
        String sql = "INSERT INTO employee(first_name, last_name, email, salary) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setDouble(4, emp.getSalary());
            ps.executeUpdate();
            System.out.println("Employee inserted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Get All Employees
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection con = DriverManager.getConnection(url, user, pass);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee(
                    rs.getInt("emp_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getDouble("salary")
                );
                list.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Get Employee by ID
    public Employee getEmployeeById(int empId) {
        String sql = "SELECT * FROM employee WHERE emp_id=?";
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(
                    rs.getInt("emp_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("email"),
                    rs.getDouble("salary")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Delete Employee
    public void deleteEmployee(int empId) {
        String sql = "DELETE FROM employee WHERE emp_id=?";
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, empId);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted" : "No employee found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. Update Employee
    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employee SET first_name=?, last_name=?, email=?, salary=? WHERE emp_id=?";
        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setDouble(4, emp.getSalary());
            ps.setInt(5, emp.getEmpId());
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Employee updated" : "No employee found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
