package com.tss.violation.model;

public class SparrowSolution implements IFlyable{

	

	@Override
	public void getColor() {
		System.out.println("Sparrow Is Brown");
	}

	@Override
	public void fly() {
		System.out.println("Sparrow Fly");
	}
	
}
