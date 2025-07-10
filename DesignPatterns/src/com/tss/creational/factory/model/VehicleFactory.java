package com.tss.creational.factory.model;

public class VehicleFactory {
	public static Vehicle getVehicle(VehicleType vehicleType)
	{
		if(vehicleType == VehicleType.TWOWHEELER)
		{
			return new TwoWheeler();
		}
		if(vehicleType == VehicleType.FOURWHEELER)
		{
			return new FourWheeler();
		}
		if(vehicleType == VehicleType.HEAVYVEHICLE)
		{
			return new HeavyVehicle();
		}
		return null;
	}
}
