package com.litmus7.employeemanager.controller;

import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.exception.EmployeeNotFoundException;
import com.litmus7.employeemanager.exception.EmployeeServiceException;
import com.litmus7.employeemanager.service.EmployeeService;
import com.litmus7.employeemanager.util.Response;
import com.litmus7.employeemanager.util.ValidationUtil;

import java.util.List;

import static com.litmus7.employeemanager.constants.MessageConstants.*;

public class EmployeeController {

    private final EmployeeService employeeservice = new EmployeeService();

    public Response<Void> createEmployee(int empid, String fname, String lname, String mobile, String email, String dojStr, boolean active) {
        try {
            if (!ValidationUtil.isValidId(empid)) {
                return new Response<>("ERROR", INVALID_ID, MSG_INVALID_ID, null);
            }
            if (!ValidationUtil.isValidName(fname)) {
                return new Response<>("ERROR", INVALID_FIRST_NAME, MSG_INVALID_FIRST_NAME, null);
            }
            if (!ValidationUtil.isValidName(lname)) {
                return new Response<>("ERROR", INVALID_LAST_NAME, MSG_INVALID_LAST_NAME, null);
            }
            if (!ValidationUtil.isValidMobile(mobile)) {
                return new Response<>("ERROR", INVALID_MOBILE, MSG_INVALID_MOBILE, null);
            }
            if (!ValidationUtil.isValidEmail(email)) {
                return new Response<>("ERROR", INVALID_EMAIL, MSG_INVALID_EMAIL, null);
            }
            if (!ValidationUtil.isValidDate(dojStr)) {
                return new Response<>("ERROR", INVALID_DATE, MSG_INVALID_DATE, null);
            }

            Employee employee = new Employee(empid, fname, lname, mobile, email, dojStr, active);

            boolean created = employeeservice.createEmployee(employee);
            if (created) {
                return new Response<>("SUCCESS", CREATED, MSG_CREATED, null);
            }
            return new Response<>("FAIL", VALIDATION_FAILED, EMPLOYEE_CREATE_FAIL, null);

        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", e.getErrorCode(), e.getMessage(), null);
        } catch (Exception e) {
            return new Response<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }

    public Response<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeservice.getAllEmployees();
            if (employees.isEmpty()) {
                return new Response<>("FAIL", EMPLOYEE_NOT_FOUND, MSG_EMPLOYEE_NOT_FOUND, employees);
            }
            return new Response<>("SUCCESS", RETRIEVED, MSG_RETRIEVED, employees);
        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", e.getErrorCode(), e.getMessage(), null);
        } catch (Exception e) {
            return new Response<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }

    public Response<String> deleteEmployee(int id) {
        try {
            if (employeeservice.deleteEmployee(id)) {
                return new Response<>("SUCCESS", DELETED, MSG_DELETED, null);
            }
            return new Response<>("FAIL", EMPLOYEE_NOT_FOUND, MSG_EMPLOYEE_NOT_FOUND, null);
        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", e.getErrorCode(), e.getMessage(), null);
        } catch (Exception e) {
            return new Response<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }

    public Response<String> updateEmployee(Employee employee) {
        try {
            if (employeeservice.updateEmployee(employee)) {
                return new Response<>("SUCCESS", UPDATED, MSG_UPDATED, null);
            }
            return new Response<>("FAIL", VALIDATION_FAILED, EMPLOYEE_UPDATE_FAIL, null);
        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", e.getErrorCode(), e.getMessage(), null);
        } catch (Exception e) {
            return new Response<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }

    public Response<Employee> getEmployeeById(int id) {
        try {
            Employee employee = employeeservice.getEmployeeById(id);
            return new Response<>("SUCCESS", RETRIEVED, MSG_RETRIEVED, employee);
        } catch (EmployeeNotFoundException e) {
            return new Response<>("FAIL", EMPLOYEE_NOT_FOUND, MSG_EMPLOYEE_NOT_FOUND, null);
        } catch (EmployeeServiceException e) {
            return new Response<>("ERROR", e.getErrorCode(), e.getMessage(), null);
        } catch (Exception e) {
            return new Response<>("ERROR", UNEXPECTED_ERROR, MSG_UNEXPECTED_ERROR, null);
        }
    }
}
