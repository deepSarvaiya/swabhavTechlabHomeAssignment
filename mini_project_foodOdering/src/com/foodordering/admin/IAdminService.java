package com.foodordering.admin;

public interface IAdminService {
	boolean login(String username, String password);

	void showAdminDashboard();
}
