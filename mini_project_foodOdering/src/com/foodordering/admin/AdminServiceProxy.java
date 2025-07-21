package com.foodordering.admin;

public class AdminServiceProxy implements IAdminService {
    private IAdminService realAdminService;
    private int failedAttempts = 0;
    private static final int MAX_ATTEMPTS = 3;

    public AdminServiceProxy(IAdminService realAdminService) {
        this.realAdminService = realAdminService;
    }

    @Override
    public boolean login(String username, String password) {
        if (failedAttempts >= MAX_ATTEMPTS) {
            System.out.println("Account locked due to too many failed attempts.");
            return false;
        }
        boolean result = realAdminService.login(username, password);
        if (!result) {
            failedAttempts++;
            System.out.println("Login failed. Attempt " + failedAttempts + "/" + MAX_ATTEMPTS);
        } else {
            failedAttempts = 0; // reset on success
        }
        return result;
    }

    @Override
    public void showAdminDashboard() {
        realAdminService.showAdminDashboard();
    }
} 