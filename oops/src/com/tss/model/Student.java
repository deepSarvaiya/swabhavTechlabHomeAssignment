package com.tss.model;

public class Student {
	private int rollnumber;
	private String name;
	private int age;
	private int sub1_marks;
	private int sub2_marks;
	private int sub3_marks;
	private int average_marks;
	
	public void setRollnumber(int rollnumber)
	{
		this.rollnumber = rollnumber;
	}
	public void setname(String name)
	{
		this.name = name;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public void setMarks(int sub1_marks,int sub2_marks,int sub3_marks)
	{
		this.sub1_marks = sub1_marks;
		this.sub2_marks = sub2_marks;
		this.sub3_marks = sub3_marks;
	}
	public int getRollnumber()
	{
		return rollnumber;
	}
	public String getName()
	{
		return name;
	}
	public int getAge()
	{
		return age;
	}
	public int getSub1_Marks()
	{
		return sub1_marks;
	}
	public int getSub2_Marks()
	{
		return sub2_marks;
	}
	public int getSub3_Marks()
	{
		return sub3_marks;
	}
	public void calculate_average()
	{
		average_marks = (sub1_marks+sub2_marks+sub3_marks)/3; 
	}
	public double getAverage()
	{
		return average_marks;
	}
	public void display()
	{
		System.out.println("Name: "+name);
		System.out.println("Rollno: "+rollnumber);
		System.out.println("Age: "+age);
		System.out.println("Subject 1 Marks: "+sub1_marks);
		System.out.println("Subject 2 Marks: "+sub2_marks);
		System.out.println("Subject 3 Marks: "+sub3_marks);
		System.out.println("Average: "+average_marks);
	}
}
