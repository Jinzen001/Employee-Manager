package com.litmus7.employeemanager.controller;

import static com.litmus7.employeemanager.constants.MessageConstants.*;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeDaoException;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.Response;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeController {

	private final EmployeeService service = new EmployeeService();

    public Response<String> createEmployee(Employee employee) {
        try {
            boolean created = service.createEmployee(employee);
            if (created) {
                return new Response<>("SUCCESS", EMPLOYEE_CREATED_SUCCESS, null);
            }
            return new Response<>("FAIL", EMPLOYEE_CREATION_FAILED, null);
        } catch (IllegalArgumentException e) {
            return new Response<>("ERROR", e.getMessage(), null); // Validation error
        }  catch (EmployeeServiceException e) {
            return new Response<>("ERROR", DATABASE_ERROR + e.getMessage(), null); // DB issues
        }
    }

    public Response<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = service.getAllEmployees();
            if (employees.isEmpty()) {
                return new Response<>("FAIL", EMPLOYEE_NOT_FOUND, employees);
            }
            return new Response<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, employees);
        } catch (EmployeeServiceException e) {
        	
            return new Response<>("ERROR", DATABASE_ERROR + e.getMessage(), null);
        }
    }

    public Response<String> deleteEmployee(int id) {
        try {
            if (service.deleteEmployee(id)) {
                return new Response<>("SUCCESS", EMPLOYEE_DELETED_SUCCESS, null);
            }
            return new Response<>("FAIL", EMPLOYEE_NOT_FOUND, null);
        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", DATABASE_ERROR + e.getMessage(), null);
        }
    }

    public Response<String> updateEmployee(Employee employee) {
        try {
            if (service.updateEmployee(employee)) {
                return new Response<>("SUCCESS", EMPLOYEE_UPDATED_SUCCESS, null);
            }
            return new Response<>("FAIL", EMPLOYEE_UPDATE_FAILED, null);
        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", DATABASE_ERROR + e.getMessage(), null);
        }
    }

    public Response<Employee> getEmployeeById(int id) {
        try {
            Employee emp = service.getEmployeeById(id);
            
            return new Response<>("SUCCESS", EMPLOYEE_RETRIEVED_SUCCESS, emp);
        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", DATABASE_ERROR + e.getMessage(), null);
        }
        catch(EmployeeNotFoundException e) {
            return new Response<>("FAIL", e.getMessage(), null);

        }
    }
}
