package com.tss.model;

import com.tss.exception.InsufficientWaterException;
import com.tss.exception.OverfillException;

public class DigitalWaterDispenser {

	private int currentWaterLevel, maxCapacity;

	public DigitalWaterDispenser() {
		super();
		currentWaterLevel = 0;
		maxCapacity = 0;
	}

	public boolean fillWater(int literOfFillWater) {

		if (currentWaterLevel + literOfFillWater >= 101) {
			throw new OverfillException(literOfFillWater);
		}
		currentWaterLevel += literOfFillWater;
		return true;

	}
	
	

	public int getCurrentWaterLevel() {	
		return currentWaterLevel;
	}


	public boolean dispenseWater(int dispenseOfFillWater) {

		if (currentWaterLevel - dispenseOfFillWater <= -1 ) {
			throw new InsufficientWaterException(dispenseOfFillWater);
		}
		currentWaterLevel -= dispenseOfFillWater;
		return true;


	}
}
