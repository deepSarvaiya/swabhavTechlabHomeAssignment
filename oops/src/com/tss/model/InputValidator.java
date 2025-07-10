package com.tss.model;

public class InputValidator {

    public static boolean validateUsername(String username) {
        if (isNullOrEmpty(username)) return false;
        return checkLength(username, 3, 20);
    }

    public static boolean validatePassword(String password) {
        if (isNullOrEmpty(password)) return false;
        return checkLength(password, 8, 30);
    }

    public static boolean validateEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        return checkLength(email, 5, 50) && email.contains("@") && email.contains(".");
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private static boolean checkLength(String str, int min, int max) {
        int len = str.length();
        return len >= min && len <= max;
    }
}
