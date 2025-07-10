package com.tss.creational.factory.test;

import com.tss.creational.factory.model.CarFactory;
import com.tss.creational.factory.model.CarType;
import com.tss.creational.factory.model.ICars;

public class CarTest {

	public static void main(String[] args) {
		CarFactory factory = new CarFactory();
		
		ICars maruti = factory.makeCars(CarType.Maruti);
		maruti.start();
		maruti.stop();
		
		ICars tata = factory.makeCars(CarType.Tata);
		tata.start();
		tata.stop();
		
		ICars mahindra = factory.makeCars(CarType.Mahindra);
		mahindra.start();
		mahindra.stop();
	}

}
