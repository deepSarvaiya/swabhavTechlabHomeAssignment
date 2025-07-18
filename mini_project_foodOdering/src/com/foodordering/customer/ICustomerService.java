package com.foodordering.customer;

public interface ICustomerService {
	void register();
    void login();
    
    void customerDashboard(String username);

}
