package com.mmcoe.datastructure;

public class BinaryTree {
	private class Node{
		private int data;
		public Node left,right;

		public Node(int data) {
			this.data = data;
		}

		public int getData() {
			return data;
		}
	}

	private Node root;

	public void add(int data) {
		Node n = new Node(data); 
		if(root == null) {
			root = n;
		}
		Node temp = root;
		while(true) {
			if(data == temp.data)break;
			if(data < temp.data) {
				if(temp.left == null) {
					temp.left = n;
					break;
				}else
					temp = temp.left;
			}else {
				if(temp.right == null) {
					temp.right = n;
					break;
				}else
					temp = temp.right;
			}
		}
	}

	public void inorder(Node t) {
		if(t == null)return;
		inorder(t.left);
		System.out.println(t.data);
		inorder(t.right);
	}
	
	public void traverse() {
		inorder(root);
	}
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.add(9);
		bt.add(20);
		bt.add(5);
		bt.add(8);
		bt.traverse();
	}
}