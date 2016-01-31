package banksys.account;

import java.io.Serializable;

import banksys.account.exception.NegativeAmountException;

public class SavingsAccount extends OrdinaryAccount implements Serializable {

	public SavingsAccount(String number) {
		super(number);
	}

	public void earnInterest() {
		try {
			this.credit(this.getBalance() * 0.001);
		} catch (NegativeAmountException e) {
		}
	}
}
