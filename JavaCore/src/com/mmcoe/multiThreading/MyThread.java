package com.mmcoe.multiThreading;

public class MyThread extends Thread{
//	private int limit;
	public MyThread(String name) {
		super(name);
	}
	@Override 
	public void run() {
		String ct = Thread.currentThread().getName();
		for(int c=0;c<150;c++) {
			System.out.println(ct + ": "+ c);
		}
	}
	public static void main(String[] args) {
		MyThread t1 = new MyThread("First");
		MyThread t2 = new MyThread("Second");
		MyThread t3 = new MyThread("Third");
		t1.setPriority(MAX_PRIORITY);
		t3.setPriority(MIN_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
	}
}
