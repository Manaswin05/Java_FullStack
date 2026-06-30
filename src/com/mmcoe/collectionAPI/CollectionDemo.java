package com.mmcoe.collectionAPI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.HashSet;
public class CollectionDemo {

	public static void main(String[] args) {
		LinkedList<String> lst = new LinkedList<String>();
		lst.add("Microsoft");
		lst.add("Google");
		lst.add("Apple");
		lst.add("Apache");
		lst.add(2, "oracle");
		System.out.println("--Travesing using for loops");
		for(int i=0;i<lst.size();i++) {
			System.out.println(lst.get(i));
		}
		System.out.println("--Traversing using iterator");
		Iterator<String> itr = lst.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println("--Traversing using For-rach loop");
		for(String s : lst) {
			System.out.println(s);
		}
		System.out.println("--Traversing using For-rach loop");
		lst.forEach(System.out::println);
		
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("IBM");
		ar.add("Jboss");
		ar.addAll(lst);
		System.out.println("--Traversing using For-rach loop");
		for(String e : ar) {
			System.out.println(e);
		}
		System.out.println("HashSet");
		HashSet<String> set = new HashSet<String>(ar);
		for(String e : set) {
			System.out.println(e);
		}
		System.out.println("TreeSet");
		TreeSet<String> tree = new TreeSet<String>(set);
		for(String e : tree) {
			System.out.println(e);
		}
	}
}