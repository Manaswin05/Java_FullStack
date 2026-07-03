package com.mmcoe.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mmcoe.pojo.Accounts;
import com.mmcoe.pojo.BalanceException;

public class TestAccount {
	private Accounts a;

	@BeforeEach
	void init() {
	    a = new Accounts(10000);
	}

	@Test
	void testDeposit() {
	    assertEquals(13000, a.deposit(3000));
	}

	@Test
	void testWithdraw() throws BalanceException {
	    assertEquals(2000, a.withdraw(8000));
	}

	@Test
	void testDepositAgain() {
	    assertEquals(12000, a.deposit(2000));
	}

	@Test
	void testWithdrawAgain() throws BalanceException {
	    assertEquals(9000, a.withdraw(1000));
	}

	@Test
	void testNegativeWithdraw() {
	    assertThrows(BalanceException.class,() -> a.withdraw(11000));
	}
}