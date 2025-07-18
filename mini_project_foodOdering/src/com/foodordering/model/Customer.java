package com.foodordering.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String username;
    private String password;

    public Customer(String name, String phone, String email, String address, String username, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
