package banksys.control;

import banksys.account.AbstractAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;
import banksys.control.exception.BankTransactionException;
import banksys.control.exception.IncompatibleAccountException;
import banksys.log.AbsLogManager;
import banksys.persistence.IAccountRepository;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class BankController {

	private IAccountRepository repository;
	private AbsLogManager log;

	public BankController(IAccountRepository repository, AbsLogManager log) {
		this.repository = repository;
		this.log = log;
	}

	public void addAccount(AbstractAccount account) throws BankTransactionException {
		try {
			this.repository.create(account);
			this.log.registerAccountCreation(account);
		} catch (AccountCreationException ace) {
			throw new BankTransactionException(ace);
		}
	}

	public void removeAccount(String number) throws BankTransactionException {
		try {
			this.repository.delete(number);
			this.log.registerAccountRemoval(number);
		} catch (AccountDeletionException ade) {
			throw new BankTransactionException(ade);
		}
	}

	public void doCredit(String number, double amount) throws BankTransactionException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		try {
			account.credit(amount);
			this.log.registerAccountCredit(number, amount);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}

	}

	public void doDebit(String number, double amount) throws BankTransactionException {
		AbstractAccount account;
		try {
			account = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}
		try {
			account.debit(amount);
			this.log.registerAccountDebit(number, amount);
		} catch (InsufficientFundsException ife) {
			throw new BankTransactionException(ife);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}
	}

	public double getBalance(String number) throws BankTransactionException {
		AbstractAccount conta;
		try {
			conta = this.repository.retrieve(number);
			return conta.getBalance();
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

	}

	public void doTransfer(String fromNumber, String toNumber, double amount) throws BankTransactionException {
		AbstractAccount fromAccount;
		try {
			fromAccount = this.repository.retrieve(fromNumber);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		AbstractAccount toAccount;
		try {
			toAccount = this.repository.retrieve(toNumber);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		try {
			fromAccount.debit(amount);
			toAccount.credit(amount);
			this.log.registerAccountTranfer(fromNumber, toNumber, amount);
		} catch (InsufficientFundsException sie) {
			throw new BankTransactionException(sie);
		} catch (NegativeAmountException nae) {
			throw new BankTransactionException(nae);
		}
	}

	public void doEarnInterest(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SavingsAccount) {
			((SavingsAccount) auxAccount).earnInterest();
			this.log.registerAccountInterestEarning(number);
		} else {
			throw new IncompatibleAccountException(number);
		}
	}

	public void doEarnBonus(String number) throws BankTransactionException, IncompatibleAccountException {
		AbstractAccount auxAccount;
		try {
			auxAccount = this.repository.retrieve(number);
		} catch (AccountNotFoundException anfe) {
			throw new BankTransactionException(anfe);
		}

		if (auxAccount instanceof SpecialAccount) {
			((SpecialAccount) auxAccount).earnBonus();
			this.log.registerAccountBonusEarning(number);
		} else {
			throw new IncompatibleAccountException(number);
		}
	}
}
