package com.tss.creational.abstractfactory.model;

public class TataCarFactory implements ICarFactory{

	@Override
	public ICars makeCars() {
		// TODO Auto-generated method stub
		return new Tata();
	}

}
