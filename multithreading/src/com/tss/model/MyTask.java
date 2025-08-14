package com.tss.model;

public class MyTask implements Runnable{

	@Override
	public void run() {
//		for(int i=5;i>=1;i--)
//		{
			System.out.println(Thread.currentThread().getName());
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
}
