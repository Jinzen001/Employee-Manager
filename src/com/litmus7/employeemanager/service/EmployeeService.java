package com.litmus7.employeemanager.service;
import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;

public class EmployeeService {
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);
    private final EmployeeDAO dao = new EmployeeDAO();

    public boolean createEmployee(Employee employee) throws EmployeeServiceException {
    	logger.info("Attempting to create employee: {}", employee);
        try {
        	int flag=dao.createEmployee(employee);
            logger.info("Employee creation {} for ID: {}", flag>0 ? "successful" : "failed");

        return flag > 0;
        
    }catch (EmployeeDaoException e) {
    	logger.error("Error while creating employee: {}", employee, e);
    	throw new EmployeeServiceException("Service Layer Unable to create Employee", e);
	}
       }
    
    public List<Employee> getAllEmployees() throws EmployeeServiceException {
    	logger.info("Fetching all employees");
    	try {
    		List<Employee>employee=dao.getAllEmployees();
    		logger.info("Retrieved {} employees", employee.size());
            return employee;

    	}catch (EmployeeDaoException e) {
    		logger.error("Error while fetching employee details", e);
    		throw new EmployeeServiceException("Service Layer Unable to fetch Employee Details",e);
		}
    }

    public boolean deleteEmployee(int id) throws EmployeeServiceException {
    	logger.info("Attempting to delete employee with ID: {}", id);
    	try {
    		int flag=dao.deleteEmployee(id);
            logger.info("Employee deletion {} for ID: {}", flag>0 ? "successful" : "failed", id);

            return flag > 0;

    	}catch (EmployeeDaoException e) {
    		throw new EmployeeServiceException("Service Layer Unable to Delete Employee", e);
		}
    }

    public boolean updateEmployee(Employee employee) throws EmployeeServiceException {
    	logger.info("Attempting to update employee: {}", employee);
        try {
            boolean result = dao.updateEmployee(employee) > 0;
            logger.info("Employee update {} for: {}", result ? "successful" : "failed", employee);
            return result;
        } catch (EmployeeDaoException e) {
            logger.error("Error while updating employee: {}", employee, e);
            throw new EmployeeServiceException("Service Layer Unable to Update Employee", e);
        }
    }
    	
    

    public Employee getEmployeeById(int id) throws EmployeeServiceException, EmployeeNotFoundException {
        logger.info("Fetching employee with ID: {}", id);
        try {
            Employee employee = dao.getEmployeeById(id);
            if (employee == null) {
                logger.warn("No employee found with ID: {}", id);
            } else {
                logger.info("Employee found: {}", employee);
            }
            return employee;
        } catch (EmployeeDaoException e) {
            logger.error("Error while retrieving employee with ID: {}", id, e);
            throw new EmployeeServiceException("Service Layer Unable to retrieve Employee", e);
        }
    }

}
