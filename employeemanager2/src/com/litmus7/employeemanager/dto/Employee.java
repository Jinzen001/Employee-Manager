package com.litmus7.employeemanager.dto;

import java.time.LocalDate;

public class Employee {
private int  Empid;
private String EmpFirstname;
private String EmpLastname;
private String MobileNumber;
private String Email;
private LocalDate Dateofjoin ;
private boolean Active;
public Employee(int empid, String firstName, String lastName,
        String mobileNumber, String email, LocalDate dateOfJoin, boolean active) {
this.Empid = empid;
this.EmpFirstname = firstName;
this.EmpLastname = lastName;
this.MobileNumber = mobileNumber;
this.Email = email;
this.Dateofjoin = dateOfJoin;
this.Active = active;
}

public Employee() {
	// TODO Auto-generated constructor stub
}

public void setId(Integer id) {
	this.Empid=id;	
}
public void setFirstName(String fname) {
	this.EmpFirstname=fname;
		
	}
public void setLastName(String lname) {
this.EmpLastname=lname;	
}
public void setMobileNumber(String earr) {
	this.MobileNumber=earr;
	
}
public void setEmail(String email) {
	this.Email=email;
	
}
public void setDateofJoin(LocalDate d) {
	this.Dateofjoin=d;
	
}
public void ActiveorNot(boolean b) {
	this.Active=b;
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

public LocalDate getDateofJoin() {
    return this.Dateofjoin;
}

public boolean isActive() {
    return this.Active;
}
public boolean getActiveStatus() {
	// TODO Auto-generated method stub
	return false;
}


}
