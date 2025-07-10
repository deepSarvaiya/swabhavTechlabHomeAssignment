package com.tss.violation.test;

import com.tss.violation.model.OstrichSolution;
import com.tss.violation.model.SparrowSolution;

public class IBirdTest {

	public static void main(String[] args) {

		SparrowSolution b1 = new SparrowSolution();
		b1.getColor();
		b1.fly();

		OstrichSolution o1 = new OstrichSolution();
		o1.getColor();
		o1.walk();
	}

}
