package com.tss.model;

public class Book {

	private int bookid,price;
	private String name,author,publication;
	
	public void setPrice(int price) {
		this.price = price;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setPublication(String publication) {
		this.publication = publication;
	}
	
	
	public int getBookid() {
		return bookid;
	}
	public int getPrice() {
		return price;
	}
	public String getName() {
		return name;
	}
	public String getAuthor() {
		return author;
	}
	public String getPublication() {
		return publication;
	}
	
	public void calculateDiscount() {
		this.price = (int)(price * (0.9));
		
	}
	public void display() {
		System.out.println("book ID: "+ this.bookid);
		System.out.println("book author: "+this.author);
		System.out.println("book Name: "+this.name);
		System.out.println("book price: "+this.price);
		System.out.println("book publication: "+ this.publication);
		
	}
}
