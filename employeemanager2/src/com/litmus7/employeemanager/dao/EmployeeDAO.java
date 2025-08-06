package com.litmus7.employeemanager.dao;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public int createEmployee(Employee employee) {
        String query = "INSERT INTO employee (first_name, last_name, mobile_number, email, date_of_join, active) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getMobileNumber());
            statement.setString(4, employee.getEmail());
            statement.setDate(5, Date.valueOf(employee.getDateofJoin()));
            statement.setBoolean(6, employee.isActive());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Employee> getAllEmployees() {
        String query = "SELECT emp_id, first_name, last_name, mobile_number, email, date_of_join, active FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("emp_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("mobile_number"),
                        rs.getString("email"),
                        rs.getDate("date_of_join").toLocalDate(),
                        rs.getBoolean("active")
                ));
            }
            return employees;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public Employee getEmployeeById(int empId) {
        String query = "SELECT emp_id, first_name, last_name, mobile_number, email, date_of_join, active FROM employee WHERE emp_id=?";
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, empId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return new Employee(
                            rs.getInt("emp_id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("mobile_number"),
                            rs.getString("email"),
                            rs.getDate("date_of_join").toLocalDate(),
                            rs.getBoolean("active")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteEmployee(int empId) {
        String query = "DELETE FROM employee WHERE emp_id=?";
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, empId);
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateEmployee(Employee employee) {
        String query = "UPDATE employee SET first_name=?, last_name=?, mobile_number=?, email=?, date_of_join=?, active=? WHERE emp_id=?";
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getMobileNumber());
            statement.setString(4, employee.getEmail());
            statement.setDate(5, Date.valueOf(employee.getDateofJoin()));
            statement.setBoolean(6, employee.isActive());
            statement.setInt(7, employee.getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
