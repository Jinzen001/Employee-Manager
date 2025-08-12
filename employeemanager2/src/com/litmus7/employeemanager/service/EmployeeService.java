package com.litmus7.employeemanager.service;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();

    public boolean createEmployee(Employee employee) throws EmployeeServiceException {
        try {
        return dao.createEmployee(employee) > 0;
    }catch (EmployeeDaoException e) {
    	throw new EmployeeServiceException("Service Layer Unable to create Employee", e);
	}
       }

    public List<Employee> getAllEmployees() throws EmployeeServiceException {
    	try {
            return dao.getAllEmployees();

    	}catch (SQLException e) {
    		throw new EmployeeServiceException("Service Layer Unable to fetch Employee Details",e);
		}
    }

    public boolean deleteEmployee(int id) throws EmployeeServiceException {
    	try {
            return dao.deleteEmployee(id) > 0;

    	}catch (SQLException e) {
    		throw new EmployeeServiceException("Service Layer Unable to Delete Employee", e);
		}
    }

    public boolean updateEmployee(Employee employee) throws EmployeeServiceException {
    	try {
    	return dao.updateEmployee(employee) > 0;
    	}catch (SQLException e) {
    		throw new EmployeeServiceException("Service Layer Unable to Update Employee", e);
		}
    }
    	
    

    public Employee getEmployeeById(int id) throws EmployeeServiceException,EmployeeNotFoundException {
        try{
        	return dao.getEmployeeById(id); // returns null if not found
        }catch (SQLException e) {
    		throw new EmployeeServiceException("Service Layer Unable to retrieve Employee", e);

		}
    }
}
