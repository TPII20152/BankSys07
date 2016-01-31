package banksys.account;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class TaxAccountTest {

	TaxAccount account;
	String accountNum = "1234";

	@Before
	public void setUp() {
		account = new TaxAccount(accountNum);
	}
	
	@Test
	public void testDebit() {
		double amount = 100;
		try {
			account.credit(amount);
		} catch (NegativeAmountException e) {}
		
		try {
			account.debit(amount/2);
		} catch (NegativeAmountException e) {}
		catch (InsufficientFundsException e) {}
		
		assertEquals(amount-((amount/2)*1.001), account.getBalance(),0);
	}
	
	@Test
	public void testInsufficientBalanceDebit() {
		double amount = 1000;
		try {
			account.credit(amount);
		} catch (NegativeAmountException e) {}
		
		double debitAmount = (account.getBalance()/1.001)+1;
		double expecteBalance = account.getBalance();
		
		try {
			account.debit(debitAmount);
		} catch (NegativeAmountException e) {}
		catch (InsufficientFundsException e) {}
		
		assertEquals(expecteBalance, account.getBalance(),0);
	}
}
