package com.tss.creational.abstractfactory.model;

public class MarutiCarFactory implements ICarFactory{

	@Override
	public ICars makeCars() {
		return new Maruti();
	}

}
