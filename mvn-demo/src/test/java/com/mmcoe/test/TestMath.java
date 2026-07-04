package com.mmcoe.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mmcoe.pojo.Maths;
public class TestMath {
	private static Maths m;
	@BeforeAll
	public static void init() {
		m = new Maths();
	}
	@Test
	public void testAdd() {
		assertEquals(10, m.add(4, 6));
	}
	@Test
	public void testSub() {
		assertEquals(5, m.sub(10, 5));
	}
	@Test
	public void testDiv() {
		assertEquals(3,m.div(9, 3));
	}
	@Test
	public void testMul() {
		assertEquals(9, m.mul(3, 3));
	}
	@Test
	public void testNegDiv() {
		assertThrows(ArithmeticException.class,()->m.div(2,0));
	}
}
