package com.litmus7.employeemanager.employee;

public class Employee {
    private int empId;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;

    public Employee() {}

    public Employee(int empId, String firstName, String lastName, String email, double salary) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
    }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String toString() {
        return empId + " - " + firstName + " " + lastName + " - " + email + " - " + salary;
    }
}

