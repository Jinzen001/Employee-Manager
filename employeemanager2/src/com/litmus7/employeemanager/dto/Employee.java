package com.litmus7.employeemanager.dto;

public class Employee {
    private int Empid;
    private String EmpFirstname;
    private String EmpLastname;
    private String MobileNumber;
    private String Email;
    private String Dateofjoin;
    private boolean Active;

    public Employee(int empid, String firstName, String lastName,
                    String mobileNumber, String email, String dojStr, boolean active) {
        this.Empid = empid;
        this.EmpFirstname = firstName;
        this.EmpLastname = lastName;
        this.MobileNumber = mobileNumber;
        this.Email = email;
        this.Dateofjoin = dojStr;
        this.Active = active;
    }

    public Employee() {}

    public void setId(Integer id) {
        this.Empid = id;    
    }
    public void setFirstName(String fname) {
        this.EmpFirstname = fname;
    }
    public void setLastName(String lname) {
        this.EmpLastname = lname;    
    }
    public void setMobileNumber(String earr) {
        this.MobileNumber = earr;
    }
    public void setEmail(String email) {
        this.Email = email;
    }
    public void setDateofJoin(String d) {
        this.Dateofjoin = d;
    }
    public void ActiveorNot(boolean b) {
        this.Active = b;
    }

    public Integer getId() {
        return this.Empid;
    }
    public String getFirstName() {
        return this.EmpFirstname;
    }
    public String getLastName() {
        return this.EmpLastname;
    }
    public String getMobileNumber() {
        return this.MobileNumber;
    }
    public String getEmail() {
        return this.Email;
    }
    public String getDateofJoin() {
        return this.Dateofjoin;
    }
    public boolean isActive() {
        return this.Active;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "Empid=" + Empid +
                ", FirstName='" + EmpFirstname + '\'' +
                ", LastName='" + EmpLastname + '\'' +
                ", MobileNumber='" + MobileNumber + '\'' +
                ", Email='" + Email + '\'' +
                ", DateOfJoin='" + Dateofjoin + '\'' +
                ", Active=" + Active +
                '}';
    }
}
