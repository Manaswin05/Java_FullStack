package com.mmcoe.reflect;

import java.security.BasicPermission;

public class Person {
	private String name;
	private int age;
	public Person() {
	}
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	private void print() {
		//Security Enhancement
		SecurityManager mgr = new SecurityManager();
		mgr.checkPermission(new BasicPermission("Private Method") {
		});
		System.out.println("Name: "+ name + ", Age: "+age);
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
}