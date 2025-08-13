package com.litmus7.employeemanager.dao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.util.DatabaseConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
	private static final Logger logger = LogManager.getLogger(EmployeeDAO.class);

    private static final String INSERT_EMPLOYEE =
            "INSERT INTO employee (emp_id, first_name, last_name, mobile_number, email, date_of_join, active) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_EMPLOYEES =
            "SELECT emp_id, first_name, last_name, mobile_number, email, date_of_join, active FROM employee";

    private static final String SELECT_EMPLOYEE_BY_ID =
            "SELECT emp_id, first_name, last_name, mobile_number, email, date_of_join, active FROM employee WHERE emp_id=?";

    private static final String DELETE_EMPLOYEE =
            "DELETE FROM employee WHERE emp_id=?";

    private static final String UPDATE_EMPLOYEE =
            "UPDATE employee SET first_name=?, last_name=?, mobile_number=?, email=?, date_of_join=?, active=? WHERE emp_id=?";


    public int createEmployee(Employee employee) throws EmployeeDaoException {
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE)) {
        	logger.info("JDBC Driver loaded successfully.");
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getMobileNumber());
            statement.setString(5, employee.getEmail());
            statement.setDate(6, Date.valueOf(employee.getDateofJoin()));
            statement.setBoolean(7, employee.isActive());

            logger.info("Rows inserted: {}", statement.executeUpdate());
            return statement.executeUpdate();
        }
        catch(SQLException e){
        	 logger.error("Failed to create employee  ", e);
        	throw new EmployeeDaoException("Database error while creating employee.",e);        	
        }
    }

    public List<Employee> getAllEmployees() throws EmployeeDaoException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DatabaseConnectionUtil.getConnection();
        		
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_EMPLOYEES);
        		
             ResultSet rs = statement.executeQuery()) {
        	logger.info("JDBC Driver loaded successfully.");


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
            logger.info("Total employees fetched: {}", employees.size());

            return employees;
        }catch(SQLException e){
       	 logger.error("Failed to Get  employee Details  ", e);
       	throw new EmployeeDaoException("Database error while fetching employee details.",e);        	
       }
    }

    public Employee getEmployeeById(int empId) throws EmployeeNotFoundException,EmployeeDaoException{
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {

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
                else {
                	throw new EmployeeNotFoundException("Employee with ID " + empId + " notfound.");

                }
            }
        }
        catch(SQLException e){
         	 logger.error("Failed to Get  employee Details  ", e);
           	throw new EmployeeDaoException("Database error while fetching employee details.",e);        	
           }
    }

    public int deleteEmployee(int empId) throws  EmployeeDaoException{
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE)) {

            statement.setInt(1, empId);
            return statement.executeUpdate();
        }
        catch(SQLException e){
         	 logger.error("Failed to delete  employee Details  ", e);
           	throw new EmployeeDaoException("Database error while deleting employee details.",e);        	
           }
    }

    public int updateEmployee(Employee employee) throws EmployeeDaoException {
        try (Connection connection = DatabaseConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EMPLOYEE)) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getMobileNumber());
            statement.setString(4, employee.getEmail());
            statement.setDate(5, Date.valueOf(employee.getDateofJoin()));
            statement.setBoolean(6, employee.isActive());
            statement.setInt(7, employee.getId());

            return statement.executeUpdate();
        }
        catch(SQLException e){
        	 logger.error("Failed to update  employee Details  ", e);
          	throw new EmployeeDaoException("Database error while updating employee details.",e);        	
          }
    }
}
