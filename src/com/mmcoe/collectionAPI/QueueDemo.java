package com.mmcoe.collectionAPI;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Deque;

public class QueueDemo {

	public static void main(String[] args) {
		Queue<String> que = new LinkedList<String>();
		que.offer("Apple");
		que.offer("Banana");
		que.offer("Cherr");
		System.out.println(que.peek());
		System.out.println("----------QUEUE");
		while(!que.isEmpty())
			System.out.println(que.poll());
		Deque<String> dq = new LinkedList<String>();
		dq.offerFirst("apple");
		dq.offerLast("Banana");
		dq.offerLast("Cherry");
		System.out.println(dq.peek());
		System.out.println("----------De-QUEUE");
		while(!dq.isEmpty()) {
			System.out.println(dq.pollFirst());
		}
	}
}