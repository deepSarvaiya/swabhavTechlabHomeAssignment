package com.tss.creational.abstractfactory.model;

public class Mahindra implements ICars {

	@Override
	public void start() {
		System.out.println("Mahindra Strts");
	}

	@Override
	public void stop() {
		System.out.println("Mahindra Stops");
	}

}
