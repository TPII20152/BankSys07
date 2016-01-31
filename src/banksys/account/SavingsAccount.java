package banksys.account;

import java.io.Serializable;

import banksys.account.exception.NegativeAmountException;

public class SavingsAccount extends OrdinaryAccount implements Serializable {
	public static double saving = 0.001;
	
	public SavingsAccount(String number) {
		super(number);
	}

	public void earnInterest() {
		try {
			this.credit(this.getBalance() * saving);
		} catch (NegativeAmountException e) {
		}
	}
}
