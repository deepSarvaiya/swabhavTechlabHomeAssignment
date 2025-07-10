package com.tss.model;

public class Labour2 implements IWorker2 {

	@Override
	public void start() {
		System.out.println("Start Working");
	}

	@Override
	public void stop() {
		System.out.println("End Working");
	}

	@Override
	public void eat() {
		System.out.println("Started Eating");
	}

	@Override
	public void rest() {
		System.out.println("Resting");
	}
}
