package com.litmus7.employeemanager.constants;

public class MessageConstants {

    // Success codes
    public static final String SUCCESS = "1000";
    public static final String CREATED = "1001";
    public static final String RETRIEVED = "1002";
    public static final String UPDATED = "1003";
    public static final String DELETED = "1004";

    // Validation / Client errors
    public static final String VALIDATION_FAILED = "2000";
    public static final String INVALID_ID = "2001";
    public static final String INVALID_FIRST_NAME = "2002";
    public static final String INVALID_LAST_NAME = "2003";
    public static final String INVALID_MOBILE = "2004";
    public static final String INVALID_EMAIL = "2005";
    public static final String INVALID_DATE = "2006";

    // Not found errors
    public static final String EMPLOYEE_NOT_FOUND = "3000";

    // Server / DB errors
    public static final String DATABASE_ERROR = "4000";
    public static final String UNEXPECTED_ERROR = "4001";

    // Messages
    public static final String MSG_SUCCESS = "Success";
    public static final String MSG_CREATED = "Employee created successfully";
    public static final String MSG_RETRIEVED = "Employee retrieved successfully";
    public static final String MSG_UPDATED = "Employee updated successfully";
    public static final String MSG_DELETED = "Employee deleted successfully";

    public static final String MSG_VALIDATION_FAILED = "Validation failed";
    public static final String MSG_INVALID_ID = "Employee ID must be a positive integer";
    public static final String MSG_INVALID_FIRST_NAME = "First name cannot be empty";
    public static final String MSG_INVALID_LAST_NAME = "Last name cannot be empty";
    public static final String MSG_INVALID_MOBILE = "Mobile number must be exactly 10 digits";
    public static final String MSG_INVALID_EMAIL = "Invalid email format";
    public static final String MSG_INVALID_DATE = "Invalid date format. Please use dd-MM-yyyy";

    public static final String MSG_EMPLOYEE_NOT_FOUND = "Employee not found";

    public static final String MSG_DATABASE_ERROR = "Database error occurred";
    public static final String MSG_UNEXPECTED_ERROR = "Unexpected error occurred";

    // App messages
    public static final String EXIT_MESSAGE = "Exiting program...";
    public static final String INVALID_CHOICE = "Invalid choice. Please try again.";
    public static final String EMPLOYEE_CREATE_FAIL = "Failed to create employee";
    public static final String EMPLOYEE_UPDATE_FAIL = "Failed to update employee";
    public static final String EMPLOYEE_DELETE_FAIL = "Failed to delete employee";

    private MessageConstants() {}
}
