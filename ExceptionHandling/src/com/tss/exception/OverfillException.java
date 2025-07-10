package com.tss.exception;

public class OverfillException extends RuntimeException {
	
	private int literOfWater;

	public OverfillException(int literOfWater) {
		super();
		this.literOfWater = literOfWater;
	}
	
	public String getMessage()
	{
		return "liter of filling water is not valid(overfill) "+literOfWater;
	}

}
