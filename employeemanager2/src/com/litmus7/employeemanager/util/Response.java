package com.litmus7.employeemanager.util;

public class Response<T> {
    private String status;   // "SUCCESS", "FAIL", "ERROR"
    private String code;     // error/success code from ErrorCodes
    private String message;  // human-readable message
    private T data;          // payload

    public Response(String status, String code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}
