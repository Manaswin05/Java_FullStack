package com.mmcoe.datastructure;

public class LinkedList<T> {
	private class Node{
		private T data;
		public  Node next;
		
		public Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}
	}

	private Node head, last;
	private int size;

	public void add(T data) {
		Node curr = new Node(data);
		if(head == null) {
			head = curr;
		}else {
			last.next = curr;
		}
		last = curr;
		size++;
	}

	public void print() {
		Node curr = head;
		while(curr != null) {
			System.out.println(curr.data);
			curr = curr.next;
		}
	}

	public void insert(int idx,T data) {
		if(idx > size) {
			throw new IllegalArgumentException("Index out of bound");
		}
		Node ins = new Node(data);
		if(idx == 0) {
			ins.next = head;
			head = ins;
			return;
		}
		Node curr = head;
		for(int cnt = 0;cnt<idx-1;cnt++) {
			curr = curr.next;
		}
		Node temp = curr.next;
		curr.next = ins;
		ins.next = temp;
		size++;
	}

	public void remove(T d) {
		Node curr = head;
		Node curr2 = head;
		while(curr.data != d) {
			curr = curr.next;
		}
		while(curr2.next != curr) {
			curr2 = curr2.next;
		}
		Node temp = curr.next;
		curr2.next = temp;
		size--;
	}

	public void removeidx(int d) {
		if(d == 0) {
			head = head.next;
			return;
		}
		Node curr = head;
		Node curr2 = head;
		for(int cnt = 0;cnt <= d-1;cnt++) {
			curr = curr.next;
		}
		while(curr2.next != curr) {
			curr2 = curr2.next;
		}
		Node temp = curr.next;
		curr2.next = temp;
		size--;
	}
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(30);
		list.add(20);
		list.add(10);
		list.insert(3,60);
		list.print();
	}
}