package com.mmcoe.collectionAPI;
import java.util.HashMap;

public class MapDemo {

	public static void main(String[] args) {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("scott", "Tiger");
		map.put("jack", "jill");
		map.put("polo", "lili");
		map.put("jack", "rose");
		System.out.println("Scott : " + map.get("scott"));
		System.out.println("polo : " + map.get("polo"));
		System.out.println("jack : " + map.get("jack"));
		System.out.println("for each loop");
		map.forEach((key,value) -> System.out.println(key + " : " + value));
	}

}
