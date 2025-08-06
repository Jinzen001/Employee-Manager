package com.litmus7.employeemanager.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.litmus7.employeemanager.controller.EmployeeController;
import com.litmus7.employeemanager.dto.Employee;
import com.litmus7.employeemanager.util.Response;

public class EmployeeManagerApp {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeController controller = new EmployeeController();

        while (true) {
            System.out.println("\nChoose an option:\n" +
                    "1. Create employee (DB)\n" +
                    "2. Get all employees (DB)\n" +
                    "3. Get employee by ID (DB)\n" +
                    "4. Update employee (DB)\n" +
                    "5. Delete employee (DB)\n" +
                    "6. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> createEmployee(sc, controller);
                case 2 -> getAllEmployees(controller);
                case 3 -> getEmployeeById(sc, controller);
                case 4 -> updateEmployee(sc, controller);
                case 5 -> deleteEmployee(sc, controller);
                case 6 -> {
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void createEmployee(Scanner sc, EmployeeController controller) {
        try {
            System.out.print("Enter Employee ID: ");
            int id = sc.nextInt();
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
            LocalDate doj;
            try {
                doj = LocalDate.parse(dojStr, FORMATTER);
            } catch (Exception e) {
                System.out.println("Invalid date format! Please use dd-MM-yyyy.");
                return;
            }

            System.out.print("Is Active (true/false): ");
            boolean active = sc.nextBoolean();
            sc.nextLine(); 

            Employee employee = new Employee(id, fname, lname, mobile, email, doj, active);
            Response<String> response = controller.createEmployee(employee);
            System.out.println(response.getMessage());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void getAllEmployees(EmployeeController controller) {
        Response<?> response = controller.getAllEmployees();
        System.out.println(response.getMessage());
        if (response.getData() != null) {
            ((java.util.List<Employee>) response.getData()).forEach(EmployeeManagerApp::printEmployee);
        }
    }

    private static void getEmployeeById(Scanner sc, EmployeeController controller) {
        System.out.print("Enter Employee ID: ");
        int id = sc.nextInt();
        Response<Employee> response = controller.getEmployeeById(id);
        System.out.println(response.getMessage());
        if (response.getData() != null) {
            printEmployee(response.getData());
        }
    }

    private static void updateEmployee(Scanner sc, EmployeeController controller) {
        System.out.println("Enter Employee ID to update: ");
        int id = sc.nextInt();

        System.out.println("Enter New details (FirstName LastName Mobile Email dd-MM-yyyy Active(true/false)): ");
        String fname = sc.next();
        String lname = sc.next();
        String mobile = sc.next();
        String email = sc.next();
        String dojStr = sc.next();
        boolean active = sc.nextBoolean();

        Employee employee = new Employee(id, fname, lname, mobile, email, LocalDate.parse(dojStr, FORMATTER), active);
        Response<String> response = controller.updateEmployee(employee);
        System.out.println(response.getMessage());
    }

    private static void deleteEmployee(Scanner sc, EmployeeController controller) {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        Response<String> response = controller.deleteEmployee(id);
        System.out.println(response.getMessage());
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
}
