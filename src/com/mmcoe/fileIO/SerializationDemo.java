package com.mmcoe.fileIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.mmcoe.personTree.Person;

public class SerializationDemo {
	public static void main(String[] args) throws Exception {
		Person p = new Person(21, "Polo");
		System.out.println(p);
		
		String path = "src/pers.txt";
		ObjectOutputStream ostream = null;
		ObjectInputStream istream = null;
		
		// Serialization code
		ostream = new ObjectOutputStream(new FileOutputStream(path));
		ostream.writeObject(p);	// serializing object
		ostream.close();
		System.out.println("Object serialized...");
		
		// Deserialization code
		istream = new ObjectInputStream(new FileInputStream(path));
		Object obj = istream.readObject();	// Deserializing object
		System.out.println(obj);
		istream.close();
	}
}
