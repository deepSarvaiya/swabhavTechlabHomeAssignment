package com.tss.model;

public class Box {

	int width;
	int height;
	int depth;

	public void initialize() {
		width = 10;
		height = 20;
		depth = 30;

	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getDepth() {
		return depth;
	}

	public void display() {
		System.out.println("Width= " + width);
		System.out.println("height= " + height);
		System.out.println("depth= " + depth);

	}

}
