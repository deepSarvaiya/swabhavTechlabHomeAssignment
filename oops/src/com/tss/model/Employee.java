package com.tss.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class Employee {
	private int empid, salary,experience;
	private String name, joiningDate;

	public Employee(){
		empid=0;
		salary=0;
		name="";
		joiningDate="";
	}
	public Employee(int empid,int salary,String name,String joiningDate){
		this.empid=empid;
		this.salary=salary;
		this.name=name;
		this.joiningDate=joiningDate;
	}
	public void salary(int salary) {
		this.salary = salary;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public int getEmpid() {
		return empid;
	}

	public int getSalary() {
		return salary;
	}

	public String getName() {
		return name;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void calculateBonus() {
		this.salary += (int) (salary * 0.5);
	}

	public void calculateExperience() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate joinDate = LocalDate.parse(this.joiningDate, formatter);
			LocalDate currentDate = LocalDate.now();
			Period period = Period.between(joinDate, currentDate);
			this.experience = period.getYears();
			
		} catch (Exception e) {
			System.out.println("Error parsing joining date");
		}
	}

	public void display() {
		System.out.println("Employee ID: " + empid);
		System.out.println("Name: " + name);
		System.out.println("Salary: " + salary);
		System.out.println("Joining Date: " + joiningDate);
		calculateExperience();
		System.out.println("Experience: " + experience);

	}
}
