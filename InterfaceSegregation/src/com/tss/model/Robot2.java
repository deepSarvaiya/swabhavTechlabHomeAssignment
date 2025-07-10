package com.tss.model;

public class Robot2 implements IWorkable {

	@Override
	public void start() {
		System.out.println("Started Working");
	}

	@Override
	public void stop() {
		System.out.println("Stop Working");
	}

}
