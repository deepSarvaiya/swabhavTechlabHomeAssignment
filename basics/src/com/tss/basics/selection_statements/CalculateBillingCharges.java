package com.tss.basics.selection_statements;


import java.util.Scanner;

public class CalculateBillingCharges {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of unit consumed :-");
		float units = sc.nextFloat();
		
		caculatewaterbill(units);
		
		sc.close();


	}
	private static void caculatewaterbill(float units)
	{
		int meter_charge = 75;
		float charge;	
		float total_water_bill;
		if(units<=100)
		{
			charge = (units*5);
		}
		else
		{
			if(units<=250)
			{
				float new_unites = units - 100 ;
				
				charge = (100 * 5) + new_unites * 10 ;  
				
			}
			else
			{
				float new_unites = units - 2530 ;
				
				charge =  (100 * 5) +  (150 * 10) + new_unites * 20 ; 
			}
		}
		
		total_water_bill = meter_charge + charge;
		System.out.println("Total Water bill is: "+total_water_bill);
		
	}
}
