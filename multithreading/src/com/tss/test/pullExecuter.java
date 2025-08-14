package com.tss.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.tss.model.MyTask;
import com.tss.model.NewTask;

public class pullExecuter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ExecutorService service=Executors.newCachedThreadPool();
//		service.submit(new MyTask());
//		service.submit(new MyTask());
//		service.submit(new MyTask());
//		service.submit(new MyTask());
//		service.shutdown();
//		
		
		 ExecutorService service1 = Executors.newFixedThreadPool(2);
	        service1.submit(new MyTask());
	        service1.submit(new MyTask());        
	        service1.submit(new MyTask());
	        service1.submit(new MyTask());
	        service1.shutdown();
	        
	        
	        
	        ExecutorService service=Executors.newSingleThreadExecutor();
	        Future<Integer> future= service.submit(new NewTask());

	        try {
	        System.out.println(future.get());
	        } catch (InterruptedException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        } catch (ExecutionException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        
	        }

	        service.shutdown();
		}

}
