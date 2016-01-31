package banksys.log;

import banksys.account.AbstractAccount;

public abstract interface AbsLogManager {
	
	public void registerAccountCreation(AbstractAccount account);
	public void registerAccountRemoval(AbstractAccount account);
	public void registerAccountCredit(AbstractAccount account, double amount);
	public void registerAccountDebit(AbstractAccount account, double amount);
	public void registerAccountTranfer(AbstractAccount originAccount, AbstractAccount destinyAccount, double amount);
	public void registerAccountInterestEarning(AbstractAccount account);
	public void registerAccountBonusEarning(AbstractAccount account);
	public void registerSectionStarting();
	public void registerSectionEnding();
}
