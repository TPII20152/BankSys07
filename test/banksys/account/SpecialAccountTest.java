package banksys.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.exception.NegativeAmountException;

public class SpecialAccountTest {

	SpecialAccount account;
	String accountNum = "1234";

	@Before
	public void setUp() {
		account = new SpecialAccount(accountNum);
	}
	
	@Test
	public void testCredit() {
		double amount = 100;
		try {
			account.credit(amount);
		} catch (NegativeAmountException e) {}
		
		assertEquals(amount*0.01, account.getBonus(),0);		
	}

	@Test
	public void testEarnBonus() {
		try {
			account.credit(100);
		} catch (NegativeAmountException e) {}
		
		double originalBalance = account.getBalance();
		double bonus = account.getBonus();
		
		account.earnBonus();
		
		assertEquals(originalBalance + bonus, account.getBalance(),0);
	}
}
