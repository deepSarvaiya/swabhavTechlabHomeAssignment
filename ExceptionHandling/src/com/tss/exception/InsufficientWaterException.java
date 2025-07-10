package com.tss.exception;

public class InsufficientWaterException extends RuntimeException{
	
	private int literOfWater;

	public InsufficientWaterException(int literOfWater) {
		super();
		this.literOfWater = literOfWater;
	}
	
	public String getMessage()
	{
		return "liter of dispense water is not valid(Insuffient water) "+literOfWater;
	}


}
