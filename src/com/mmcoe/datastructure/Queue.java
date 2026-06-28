package com.mmcoe.datastructure;

public class Queue<T> {
    private int front;
    private int rear;
    private int size;
    private T[] q;

    public Queue(int capacity) {
        q = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    public void enqueue(T data) {
        if (size == q.length)
            throw new IllegalArgumentException("Queue is full!");

        q[rear] = data;
        rear = (rear + 1) % q.length;
        size++;
    }

    public void dequeue() {
        if (size == 0)
            throw new IllegalArgumentException("Queue is empty!");

        front = (front + 1) % q.length;
        size--;
    }

    public T front() {
        if (size == 0)
            throw new IllegalArgumentException("Queue is empty!");
        return q[front];
    }

    public boolean empty() {
        return size == 0;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(q[(front + i) % q.length]);
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>(10);

        q.enqueue(50);
        q.enqueue(30);
        q.enqueue(20);
        q.dequeue();
        q.enqueue(45);
        q.print();
    }
}