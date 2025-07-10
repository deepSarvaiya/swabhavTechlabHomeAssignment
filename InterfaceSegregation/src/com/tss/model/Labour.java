package com.tss.model;

public class Labour implements IWorker {

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
		System.out.println("Started Eating");
	}

	@Override
	public void drink() {
		System.out.println("Started Drinking");
	}

}
