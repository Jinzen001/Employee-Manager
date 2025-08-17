package com.litmus7.employeemanager.service;

import com.litmus7.employeemanager.dao.EmployeeDAO;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.constants.ErrorCodes;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

public class EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeService.class);
    private final EmployeeDAO dao = new EmployeeDAO();

    public boolean createEmployee(Employee employee) throws EmployeeServiceException {
        logger.info("Attempting to create employee: {}", employee);
        try {
            int flag = dao.createEmployee(employee);
            logger.info("Employee creation {} for ID: {}",
                    flag > 0 ? "successful" : "failed",
                    employee.getId());
            return flag > 0;

        } catch (EmployeeDaoException e) {
            logger.error("Error while creating employee: {}", employee, e);
            throw new EmployeeServiceException(
                ErrorCodes.EMPLOYEE_CREATION_FAILED,
                "Service Layer Unable to create Employee",
                e
            );
        }
        
        catch (Exception e) {
            logger.error("Unexpected error while creating employee: {}", employee, e);
            throw new EmployeeServiceException(
                ErrorCodes.SERVICE_UNKNOWN_ERROR,
                "Unexpected error occurred while creating employee",
                e
            );
        }
    }

    public List<Employee> getAllEmployees() throws EmployeeServiceException {
        logger.info("Fetching all employees");
        try {
            List<Employee> employees = dao.getAllEmployees();
            logger.info("Retrieved {} employees", employees.size());
            return employees;

        } catch (EmployeeDaoException e) {
            logger.error("Error while fetching employee details", e);
            throw new EmployeeServiceException(
                ErrorCodes.EMPLOYEE_FETCH_FAILED,
                "Service Layer Unable to fetch Employee Details",
                e
            );
        } catch (Exception e) {
            logger.error("Unexpected error while fetching employees", e);
            throw new EmployeeServiceException(
                ErrorCodes.SERVICE_UNKNOWN_ERROR,
                "Unexpected error occurred while fetching employees",
                e
            );
        }
    }

    public boolean deleteEmployee(int id) throws EmployeeServiceException {
        logger.info("Attempting to delete employee with ID: {}", id);
        try {
            int flag = dao.deleteEmployee(id);
            logger.info("Employee deletion {} for ID: {}", flag > 0 ? "successful" : "failed", id);
            return flag > 0;

        } catch (EmployeeNotFoundException e) {
            logger.warn("Employee not found while attempting deletion. ID: {}", id, e);
            // Wrap it into a service exception with proper error code
            throw new EmployeeServiceException(
                ErrorCodes.EMPLOYEE_NOT_FOUND,
                "Employee with ID " + id + " not found",
                e
            );

        } catch (EmployeeDaoException e) {
            logger.error("Error while deleting employee with ID: {}", id, e);
            throw new EmployeeServiceException(
                ErrorCodes.EMPLOYEE_DELETE_FAILED,
                "Service Layer Unable to Delete Employee",
                e
            );

        } catch (Exception e) {
            logger.error("Unexpected error while deleting employee with ID: {}", id, e);
            throw new EmployeeServiceException(
                ErrorCodes.SERVICE_UNKNOWN_ERROR,
                "Unexpected error occurred while deleting employee",
                e
            );
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
            throw new EmployeeServiceException(
                ErrorCodes.EMPLOYEE_UPDATE_FAILED,
                "Service Layer Unable to Update Employee",
                e
            );
        } catch (Exception e) {
            logger.error("Unexpected error while updating employee: {}", employee, e);
            throw new EmployeeServiceException(
                ErrorCodes.SERVICE_UNKNOWN_ERROR,
                "Unexpected error occurred while updating employee",
                e
            );
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
            throw new EmployeeServiceException(
                ErrorCodes.EMPLOYEE_RETRIEVE_FAILED,
                "Service Layer Unable to retrieve Employee",
                e
            );
        } catch (EmployeeNotFoundException e) {
            logger.error("Service Layer Unable to retrieve Employee with ID: {}", id, e);
            throw e; // rethrow, since it's a valid business exception
        } catch (Exception e) {
            logger.error("Unexpected error while retrieving employee with ID: {}", id, e);
            throw new EmployeeServiceException(
                ErrorCodes.SERVICE_UNKNOWN_ERROR,
                "Unexpected error occurred while retrieving employee",
                e
            );
        }
    }
}
