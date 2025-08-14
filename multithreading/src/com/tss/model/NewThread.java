package com.tss.model;

public class NewThread implements Runnable {
	String name; // name of thread
	public Thread t;

	public NewThread(String threadname) {
		name = threadname;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		t.start(); // Start the thread
	}

	// This is the entry point for thread.
	public void run() {
	    try {
	        for (int i = 5; i > 0; i--) {
	            System.out.println(Thread.currentThread().getName() + ": " + i);
	            Thread.sleep(1000);
	        }
	    } catch (InterruptedException e) {
	        System.out.println(Thread.currentThread().getName() + " interrupted.");
	    }
	    System.out.println(Thread.currentThread().getName() + " exiting.");
	}

}