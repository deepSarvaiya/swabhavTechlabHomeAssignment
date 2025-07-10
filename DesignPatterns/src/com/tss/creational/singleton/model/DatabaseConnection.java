package com.tss.creational.singleton.model;

public class DatabaseConnection {
	private static DatabaseConnection connection = null;
	private DatabaseConnection()
	{
		
	}
	public static DatabaseConnection createInstance()
	{
		if(connection == null)
		{
		return connection = new DatabaseConnection();
		}
		return connection;
	}
	
}
