package com.litmus7.employeemanager.app;

import static com.litmus7.employeemanager.constants.MessageConstants.*;

import java.util.List;
import java.util.Scanner;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.Response;

public class EmployeeManagerApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeController employeecontroller = new EmployeeController();

        while (true) {
            System.out.println("\nChoose an option:\n" +
                    "1. Create employee (DB)\n" +
                    "2. Get all employees (DB)\n" +
                    "3. Get employee by ID (DB)\n" +
                    "4. Update employee (DB)\n" +
                    "5. Delete employee (DB)\n" +
                    "6. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> createEmployee(sc, employeecontroller);
                case 2 -> getAllEmployees(employeecontroller);
                case 3 -> getEmployeeById(sc, employeecontroller);
                case 4 -> updateEmployee(sc, employeecontroller);
                case 5 -> deleteEmployee(sc, employeecontroller);
                case 6 -> {
                    System.out.println(EXIT_MESSAGE);
                    sc.close();
                    return;
                }
                default -> System.out.println(INVALID_CHOICE);
            }
        }
    }

    private static void createEmployee(Scanner sc, EmployeeController controller) {
        System.out.print("Enter Employee ID: ");
        int empid = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter First Name: ");
        String fname = sc.nextLine().trim();

        System.out.print("Enter Last Name: ");
        String lname = sc.nextLine().trim();

        System.out.print("Enter Mobile Number: ");
        String mobile = sc.nextLine().trim();

        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Enter Date of Joining (dd-MM-yyyy): ");
        String dojStr = sc.nextLine().trim();

        System.out.print("Is Active (true/false): ");
        boolean active = sc.nextBoolean();
        sc.nextLine();

        Response<Void> response = controller.createEmployee(
                empid, fname, lname, mobile, email, dojStr, active);

        printResponse(response);
    }

    private static void getAllEmployees(EmployeeController controller) {
        Response<?> response = controller.getAllEmployees();
        printResponse(response);
        if (response.getData() != null) {
            ((List<Employee>) response.getData()).forEach(EmployeeManagerApp::printEmployee);
        }
    }

    private static void getEmployeeById(Scanner sc, EmployeeController controller) {
        System.out.print("Enter Employee ID: ");
        int empid = sc.nextInt();
        Response<Employee> response = controller.getEmployeeById(empid);
        printResponse(response);
        if (response.getData() != null) {
            printEmployee(response.getData());
        }
    }

    private static void updateEmployee(Scanner sc, EmployeeController controller) {
        System.out.println("Enter Employee ID to update: ");
        int empid = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter New details (FirstName LastName Mobile Email dd-MM-yyyy Active(true/false)): ");
        String fname = sc.next();
        String lname = sc.next();
        String mobile = sc.next();
        String email = sc.next();
        String dojStr = sc.next();
        boolean active = sc.nextBoolean();

        Employee employee = new Employee(empid, fname, lname, mobile, email, dojStr, active);
        Response<String> response = controller.updateEmployee(employee);
        printResponse(response);
    }

    private static void deleteEmployee(Scanner sc, EmployeeController controller) {
        System.out.print("Enter Employee ID to delete: ");
        int empid = sc.nextInt();
        Response<String> response = controller.deleteEmployee(empid);
        printResponse(response);
    }

    private static void printEmployee(Employee employee) {
        System.out.println("----------------------");
        System.out.println("ID: " + employee.getId());
        System.out.println("First Name: " + employee.getFirstName());
        System.out.println("Last Name: " + employee.getLastName());
        System.out.println("Mobile No: " + employee.getMobileNumber());
        System.out.println("Email: " + employee.getEmail());
        System.out.println("Date of Join: " + employee.getDateofJoin());
        System.out.println("Active: " + employee.isActive());
    }

    private static void printResponse(Response<?> response) {
        System.out.println(response.getStatus() + " | Code: " + response.getCode() + " | Message: " + response.getMessage());
    }
}
