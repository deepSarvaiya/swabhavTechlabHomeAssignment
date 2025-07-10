package com.tss.creational.singleton.test;

import com.tss.creational.singleton.model.DatabaseConnection;

public class DatabaseConnectionTest {

	public static void main(String[] args) {
		DatabaseConnection connection = DatabaseConnection.createInstance();
		System.out.println(connection.hashCode());
		
		DatabaseConnection connection2 = DatabaseConnection.createInstance();
		System.out.println(connection2.hashCode());

	}

}
