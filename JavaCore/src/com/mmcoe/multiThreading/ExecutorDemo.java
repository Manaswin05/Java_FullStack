package com.mmcoe.multiThreading;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class GoRunnable implements Runnable {
	private final long countUtil;

	public GoRunnable(long countUtil) {
		this.countUtil = countUtil;
	}

	public void run() {
		long sum = 0;
		for (long i = 1; i < countUtil; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}

public class ExecutorDemo {
	public static final int NTHREDS = 10;

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(NTHREDS);
		long start = System.currentTimeMillis();
		 for(int i=0;i<500;i++) {
			 Runnable worker = new GoRunnable(100000000L + i);
			 executors.execute(worker);
		 }
		 executors.shutdown();
		 while(!executors.isTerminated()) {}
		 long end = System.currentTimeMillis();
		 System.out.println("Finished All Threads in "+ (end-start) + "ms");
	}
}