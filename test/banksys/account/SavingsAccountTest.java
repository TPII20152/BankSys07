package banksys.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.exception.NegativeAmountException;

public class SavingsAccountTest {

	SavingsAccount account;
	String accountNum = "1234";

	@Before
	public void setUp() {
		account = new SavingsAccount(accountNum);
	}
	
	@Test
	public void testEarnInterest() {
		try {
			account.credit(100);
		} catch (NegativeAmountException e) {}
		
		double originalBalance = account.getBalance();
		
		account.earnInterest();
		
		assertEquals(originalBalance*1.001, account.getBalance(),0);
	}
}
