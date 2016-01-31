package banksys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class AccountVectorTest {
	AccountVector contas;
	
	@Before
	public void setup() {
		contas = new AccountVector();
	}
	
	@Test
	public void testNumeroContasVazio() {
		assertEquals(0, contas.numberOfAccounts());
	}
	
	@Test
	public void testNumeroContasPositivo() throws AccountCreationException  {
		contas.create(new OrdinaryAccount("1234"));
		assertEquals(1, contas.numberOfAccounts());
	}

	@Test (expected = AccountCreationException.class)
	public void testInserirDuplicata() throws AccountCreationException {
		contas.create(new OrdinaryAccount("1234"));
		contas.create(new OrdinaryAccount("1234"));
	}
	
	@Test
	public void testProcurarNaoExistente() {
		assertNull(contas.findAccount("1234"));
	}
	
	@Test
	public void testProcurarExistente() throws AccountCreationException  {
		String numConta = "1234";
		OrdinaryAccount conta = new OrdinaryAccount(numConta);
		contas.create(conta);
		assertEquals(conta.getNumber(),contas.findAccount(numConta).getNumber());
	}
	
	@Test (expected = AccountDeletionException.class)
	public void testApagarNaoExistente() throws AccountDeletionException {
		contas.delete("1234");
	}

	@Test
	public void testApagarExistente() throws AccountDeletionException {
		String numConta = "1234";
		OrdinaryAccount conta = new OrdinaryAccount(numConta);
		try {
			contas.create(conta);
			contas.delete(numConta);
			assertEquals(null, contas.findAccount(numConta));
		} catch (AccountCreationException | AccountDeletionException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testListar() {
		AbstractAccount[] contaVetor = new OrdinaryAccount[10];
		try {
			for (int i = 0; i < 10; i++) {
				contaVetor[i] = new OrdinaryAccount(""+i);
				contas.create(contaVetor[i]);				
			}
			
			assertEquals(contaVetor, contas.list());
		} catch (AccountCreationException e) {
			e.printStackTrace();
		}
		
		
	}
}
