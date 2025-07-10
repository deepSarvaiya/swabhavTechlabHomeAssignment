package com.tss.model;

public class Robot implements IWorker{
	@Override
	public void startWork() {
		System.out.println("Started Working");
	}

	@Override
	public void endWork() {
		System.out.println("End Work");
	}

	@Override
	public void eat() {
		
	}

	@Override
	public void drink() {
		
	}
	
	
}
