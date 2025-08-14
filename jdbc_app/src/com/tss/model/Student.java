package com.tss.model;

public class Student {

	private int studentid , rollnumber,age;
	private double percentage;
	private String name;
	public Student(int studentid, int rollnumber, int age, double percentage, String name) {
		super();
		this.studentid = studentid;
		this.rollnumber = rollnumber;
		this.age = age;
		this.percentage = percentage;
		this.name = name;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getRollnumber() {
		return rollnumber;
	}
	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
