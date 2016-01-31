package banksys.log;

import banksys.account.AbstractAccount;

public abstract interface AbsLogManager {
	
	public void registerAccountCreation(AbstractAccount account);
	public void registerAccountRemoval(String number);
	public void registerAccountCredit(String number, double amount);
	public void registerAccountDebit(String number, double amount);
	public void registerAccountTranfer(String originNumber, String destinyNumber, double amount);
	public void registerAccountInterestEarning(String number);
	public void registerAccountBonusEarning(String number);
	public void registerSectionStarting();
	public void registerSectionEnding();
}
