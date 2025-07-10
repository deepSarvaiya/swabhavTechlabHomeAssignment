package com.tss.model;

import java.util.List;

public class FDPrint {
	
	public FDPrint(FDCalculator calculator) {
		// TODO Auto-generated constructor stub
	}

	public void printAll(List<FixedDepositFestival> fdList) {
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-15s %-12s %-10s %-15s %-15s %-15s%n",
                "Acc No", "Name", "Principal", "Duration", "Festival", "Interest Rate", "Simple Interest");
        System.out.println("-------------------------------------------------------------------------------------------------");

        for (FixedDepositFestival fd : fdList) {
            System.out.printf("%-8d %-15s %-12.2f %-10d %-15s %-15.2f %-15.2f%n",
                    fd.getAccountNumber(),
                    fd.getName(),
                    fd.getPrincipal(),
                    fd.getDuration(),
                    fd.getFestival().getClass().getSimpleName(),
                    fd.getFestival().getRate(),
                    fd.calculateSimpleInterest());
        }

        System.out.println("-------------------------------------------------------------------------------------------------");
    }

}
