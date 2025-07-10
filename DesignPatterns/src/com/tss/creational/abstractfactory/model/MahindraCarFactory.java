package com.tss.creational.abstractfactory.model;

public class MahindraCarFactory implements ICarFactory{

	@Override
	public ICars makeCars() {
		// TODO Auto-generated method stub
		return new Mahindra();
	}

}
