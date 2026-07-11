package com.ex9;

import org.springframework.stereotype.Component;

@Component("temp")
public class TemperatureConverter {
	public int ctof(int c) {
		int f = (c*9/5) + 32;
		System.out.println(c + " celcius to farenheit : "+f);
		return f;
	}
	public int ftoc(int f) {
		int c = (f-32)*5/9;
		System.out.println(f + " farenheit to celcius: "+c);
		return c;
	}
}
