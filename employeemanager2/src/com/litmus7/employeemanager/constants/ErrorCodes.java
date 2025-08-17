package com.litmus7.employeemanager.constants;

public class ErrorCodes {
    public static final String EMPLOYEE_CREATION_FAILED = "EMP-001";
    public static final String EMPLOYEE_FETCH_FAILED    = "EMP-002";
    public static final String EMPLOYEE_DELETE_FAILED   = "EMP-003";
    public static final String EMPLOYEE_UPDATE_FAILED   = "EMP-004";
    public static final String EMPLOYEE_RETRIEVE_FAILED = "EMP-005";
    public static final String EMPLOYEE_NOT_FOUND       = "EMP-006";

    public static final String DB_CONNECTION_FAILED     = "DB-001";
    public static final String DB_QUERY_TIMEOUT         = "DB-002";
    public static final String DB_DUPLICATE_ENTRY       = "DB-003";

    public static final String SERVICE_UNKNOWN_ERROR    = "SRV-001";

    private ErrorCodes() {}
}
