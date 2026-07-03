package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestAssertion {
	@Test
	public void testAssertion() {
		String s1 = "Hello";
		String s2 = "Hello";
		assertEquals(s1,s2);
	}
	
}