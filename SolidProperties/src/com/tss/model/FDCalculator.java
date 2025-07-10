
package com.tss.model;

public class FDCalculator {
	public double calculateSimpleInterest(FixedDepositFestival fd) {
        return (fd.getPrincipal() * fd.getDuration() * fd.getFestival().getRate()) / 100;
	    }
}