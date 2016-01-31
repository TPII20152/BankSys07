package banksys.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class OrdinaryAccountTest {

	AbstractAccount account;
	String accountNum = "1234";

	@Before
	public void setUp() {
		account = new OrdinaryAccount(accountNum);
	}
	
	@Test
	public void testCredit() {
		double amount = 10;
		try {
			account.credit(amount);
		} catch (NegativeAmountException e) {}
		
		assertEquals(amount, account.getBalance(), 0);
	}
	
	@Test
	public void testNegativeCredit() {
		double amount = -5;
		double lastBalance = account.getBalance();
		try {
			account.credit(amount);
		} catch (NegativeAmountException e) {}
		
		assertEquals(lastBalance, account.getBalance(), 0);
	}
	
	@Test
	public void testDebit() {
		double amount = Math.random()*100;
		try {
			account.credit(amount);
		} catch (NegativeAmountException e1) {}
		
		double lastBalance = account.getBalance();
		double expectedBalance = lastBalance - amount;

		try {
			account.debit(amount);
		} catch (NegativeAmountException e) {}
		  catch (InsufficientFundsException e) {}

		assertEquals(expectedBalance, account.getBalance(), 0);
	}
	
	@Test
	public void testInsufficientBalanceDebit() {
		double amount = Math.random()*100;
		try {
			account.credit(amount);
		} catch (NegativeAmountException e1) {}
		
		double lastBalance = account.getBalance();

		try {
			account.debit(amount+1);
		} catch (NegativeAmountException e) {}
		  catch (InsufficientFundsException e) {}

		assertEquals(lastBalance, account.getBalance(), 0);
	}
	
	@Test
	public void testNegativeDebit() {
		double amount = Math.random()*100;
		try {
			account.credit(amount);
		} catch (NegativeAmountException e1) {}
		
		double lastBalance = account.getBalance();

		try {
			account.debit(-amount);
		} catch (NegativeAmountException e) {}
		  catch (InsufficientFundsException e) {}

		assertEquals(lastBalance, account.getBalance(), 0);
	}
}
