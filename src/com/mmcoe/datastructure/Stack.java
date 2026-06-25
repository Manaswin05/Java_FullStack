package com.mmcoe.datastructure;

public class Stack<T> {
	private int size;
	private T[] st;
	
	
	public Stack(int size) {
		st = (T[]) new Object[size];
	}

	public void push(T data) {
		st[size++] = data;
	}
	
	public void pop() {
		if(size == 0)
			throw new IllegalArgumentException("Stack is empty!");
		size--;
	}
	
	public T top(){
		if(size == 0)
			throw new IllegalArgumentException("Stack is empty!");
		return st[size-1];
	}
	
	public boolean empty() {
		return size == 0;
	}
	
	public void print() {
		for(int i = 0;i<size;i++) {
			System.out.println(st[i]);
		}
	}
	public static void main(String[] args) {
		Stack<Integer> st = new Stack<Integer>(10);
		st.push(50);
		st.push(30);
		st.push(20);
		st.pop();
		st.push(45);
		st.print();
	}
}