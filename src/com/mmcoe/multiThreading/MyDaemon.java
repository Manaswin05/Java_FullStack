package com.mmcoe.multiThreading;


public class MyDaemon extends Thread{
	private int limit;
	public MyDaemon(int limit,String name) {
		super(name);
		this.limit = limit;
	}
	@Override 
	public void run() {
		String ct = Thread.currentThread().getName();
		for(int c=0;c<limit;c++) {
			System.out.println(ct + ": "+ c);
		}
	}
	public static void main(String[] args) {
		MyDaemon d1 = new MyDaemon(50,"First");
		MyDaemon d2 = new MyDaemon(100,"Second");
		MyDaemon d3 = new MyDaemon(150,"Third");
		
		MyDaemon d4 = new MyDaemon(100000,"daemon");
		d4.setDaemon(true);
		
		d1.start();
		d2.start();
		d3.start();
		d4.start();
	}
}
