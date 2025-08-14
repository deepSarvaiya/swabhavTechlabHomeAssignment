package com.tss.model;

public class Student {
    private int studentId;
    private String studentName;
    private int age;
    private double percentage;
    private int rollno;

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int studentId, String studentName, int age, double percentage, int rollno) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.age = age;
        this.percentage = percentage;
        this.rollno = rollno;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    // toString() method for debugging or display
    @Override
    public String toString() {
        return "Student [studentId=" + studentId + 
               ", studentName=" + studentName + 
               ", age=" + age + 
               ", percentage=" + percentage + 
               ", rollno=" + rollno + "]";
    }
}