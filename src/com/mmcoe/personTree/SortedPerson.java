package com.mmcoe.personTree;
import java.util.Comparator;
import java.util.TreeSet;
public class SortedPerson {
	public static void main(String[] args) {
		Comparator<Person> ageComp = (p1,p2)-> p1.getAge() - p2.getAge();
		Comparator<Person> nameComp = (p1,p2)-> p1.getName().compareTo(p2.getName());
		TreeSet<Person> people = new TreeSet<Person>(nameComp);
		people.add(new Person(24, "Polo"));
		people.add(new Person(31, "Mona"));
		people.add(new Person(28, "Jack"));
		people.forEach(System.out::println);
	}
}
