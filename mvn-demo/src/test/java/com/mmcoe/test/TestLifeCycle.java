package com.mmcoe.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class TestLifeCycle {
	private List<String> items;

	@BeforeAll
	public static void beforeAll() {
		System.out.println("Executes before all test cases");
	}
	@AfterAll
		public static void afterAll() {
			System.out.println("Executes after all test cases");
		}
		@BeforeEach
		public void init() {
			items = new ArrayList<String>();
			System.out.println("Executes before each test case");
		}
		@AfterEach
		public void clean() {
			items = null;
			System.out.println("Executes after each test case");
		}
		@Test
		public void testEmptyList() {
			assertTrue(items.isEmpty());
		}
		@Test
		public void testOneItem() {
			items.add("One");
			assertEquals(1,items.size());
		}
	}
