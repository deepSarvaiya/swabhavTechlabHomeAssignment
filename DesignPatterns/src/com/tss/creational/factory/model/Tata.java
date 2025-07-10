package com.tss.creational.factory.model;

public class Tata implements ICars{

	@Override
	public void start() {
		System.out.println("Tata Start");
	}

	@Override
	public void stop() {
		System.out.println("Tata Stop");
	}

}
