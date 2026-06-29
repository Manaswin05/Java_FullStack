package com.mmcoe.collectionAPI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LocatDateDemo {

	public static void main(String[] args) {
		LocalDate d1 = LocalDate.now();
		System.out.println(d1);
		LocalDate d2 = LocalDate.of(2012,02,13);
		System.out.println(d2);
		LocalDate d3 = LocalDate.parse("2018-05-19");
		System.out.println(d3);
		System.out.println("-------------");
		System.out.println(d1.plusDays(1));
		System.out.println(d1.minus(1, ChronoUnit.MONTHS));
		System.out.println(d2.getDayOfWeek());
		System.out.println(d3.getMonth());
		System.out.println(d2.isLeapYear());
		System.out.println("--------------");
		LocalDate d4 = LocalDate.of(2005,01,27);
		System.out.println("Dob : "+ d4);
		System.out.println("AGE : " + (d1.getYear()-d4.getYear()) );
	}

}
