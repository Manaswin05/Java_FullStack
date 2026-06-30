package com.mmcoe.collectionAPI;
import java.util.ResourceBundle;
import java.util.Locale;
public class LocaleDemo {

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("msgs",Locale.of("hi"));
		System.out.println(bundle.getString("greeting"));
		System.out.println(bundle.getString("message"));
		ResourceBundle bunFR = ResourceBundle.getBundle("msgs",Locale.FRANCE);
		System.out.println(bunFR.getBundle("greeting"));
		System.out.println(bunFR.getString("message"));
	}
}