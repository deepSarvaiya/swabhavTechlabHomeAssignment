package com.tss.test;

import com.tss.model.Consumer;
import com.tss.model.Producer;
import com.tss.model.Q;

public class PC {
	public static void main(String args[]) {

		Q q = new Q();
		new Producer(q);
		new Consumer(q);
		System.out.println("Press Control-C to stop.");
	}
}