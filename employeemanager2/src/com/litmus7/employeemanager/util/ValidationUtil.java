package com.litmus7.employeemanager.util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidationUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static boolean isValidId(int id) {
        return id > 0;
    }

    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static boolean isValidMobile(String mobile) {
        return mobile != null && mobile.matches("\\d{10}");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
