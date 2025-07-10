package com.tss.test;

import com.tss.model.IWorkable;
import com.tss.model.IWorker2;
import com.tss.model.Labour2;
import com.tss.model.Robot2;

public class WorkerTest2 {

	public static void main(String[] args) {
		IWorker2 labour = new Labour2();
		labour.start();
		labour.eat();
		labour.rest();
		labour.stop();
		
		
		System.out.println("----------Robot---------");
		IWorkable robot = new Robot2();
		robot.start();
		robot.stop();
	}

}
