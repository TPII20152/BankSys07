package banksys.control;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.control.exception.BankTransactionException;
import banksys.log.FileLogManager;
import banksys.persistence.AccountVector;

public class BankControllerTest {
	BankController bc;
	
	@Before
	public void setup(){
		bc = new BankController(new AccountVector(), new FileLogManager());
	}
	
	@Test
	public void testAddAcount() throws BankTransactionException {
		AbstractAccount account = new OrdinaryAccount("1234");
		bc.addAccount(account);
		assertEquals("1234", bc.repository.list()[0].getNumber());
	}
	
	@Test
	public void testDoCredit() throws BankTransactionException {
		AbstractAccount account = new OrdinaryAccount("1234");
		bc.addAccount(account);
		bc.doCredit("1234", 100.0);
		assertEquals(100.0, bc.repository.list()[0].getBalance(), 5.0);
	}
	
	@Test
	public void testRemoveAccount() throws BankTransactionException {
		AbstractAccount account = new OrdinaryAccount("1234");
		bc.addAccount(account);
		bc.removeAccount("1234");
		assertEquals(0, bc.repository.numberOfAccounts());
	}
	
	@Test
	public void testDoDebit() throws BankTransactionException {
		AbstractAccount account = new OrdinaryAccount("1234");
		bc.addAccount(account);
		assertEquals("1234", bc.repository.list()[0].getNumber());
		bc.doCredit("1234", 50);
		bc.doDebit("1234", 25);
		assertEquals(25, bc.repository.list()[0].getBalance(), 5.0);
	}
	
	@Test
	public void testGetBalance() throws BankTransactionException {
		AbstractAccount account = new OrdinaryAccount("1234");
		bc.addAccount(account);
		bc.doCredit("1234", 30);
		assertEquals(30.0, bc.repository.list()[0].getBalance(), 5.0);
	}
	
	@Test
	public void testDoTransfer() throws BankTransactionException {
		AbstractAccount account = new OrdinaryAccount("1234");
		bc.addAccount(account);
		account = new OrdinaryAccount("4321");
		bc.addAccount(account);
		bc.doCredit("4321", 100);
		bc.doTransfer("4321", "1234", 25);
		assertEquals(25.0, bc.getBalance("1234"), 5.0);
		assertEquals(75.0, bc.getBalance("4321"), 5.0);
	}
	
	@Test
	public void testEarnInterest() throws BankTransactionException {
		AbstractAccount account = new SavingsAccount("1234");
		bc.addAccount(account);
		bc.doCredit("1234", 30);
		bc.doEarnInterest("1234");
		assertEquals(30 + (30.0 * SavingsAccount.saving), bc.getBalance("1234"), 5.0);
	}
	
	@Test
	public void testEarnBonus() throws BankTransactionException {
		SpecialAccount account = new SpecialAccount("1234");
		bc.addAccount(account);
		bc.doCredit("1234", 30);
		bc.doEarnBonus("1234");
		assertEquals(30 + (30.0 * 0.01), bc.getBalance("1234"), 5.0);
	}
}
