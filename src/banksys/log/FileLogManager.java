package banksys.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Calendar;

import banksys.account.AbstractAccount;

public class FileLogManager implements AbsLogManager {

	String filename;
	PrintStream out;
	
	public FileLogManager() {
		
		filename = getTimeString() + ".log";		
		
		try {
			FileOutputStream fo = new FileOutputStream(filename);
			out = new PrintStream(fo);					
			registerSectionStarting();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void registerAccountCreation(AbstractAccount account) {
		String entry = getTimeString() + " account " + account.getNumber() + " created.";
		
		out.println(entry);		
	}

	@Override
	public void registerAccountRemoval(AbstractAccount account) {
		String entry = getTimeString() + " account " + account.getNumber() + " removed.";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountCredit(AbstractAccount account, double amount) {
		String entry = getTimeString() + " credited " + amount + " in account " + account.getNumber() + ".";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountDebit(AbstractAccount account, double amount) {
		String entry = getTimeString() + " debited " + amount + " from account " + account.getNumber() + ".";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountTranfer(AbstractAccount originAccount, AbstractAccount destinyAccount, double amount) {
		String entry = getTimeString() + " transfered " + amount + 
					   " from account " + originAccount.getNumber() + 
					   " to account " + destinyAccount + ".";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountInterestEarning(AbstractAccount account) {
		String entry = getTimeString() + " account " + account.getNumber() + 
					   " has earned interest.";
	
		out.println(entry);	
	}

	@Override
	public void registerAccountBonusEarning(AbstractAccount account) {
		String entry = getTimeString() + " account " + account.getNumber() + 
				   " has earned bonus.";

		out.println(entry);	
	}
	
	private String getTimeString() {
		Calendar current = Calendar.getInstance();
		
		return  current.get(Calendar.DAY_OF_MONTH) + "-" +
				current.get(Calendar.MONTH) + "-" +
				current.get(Calendar.YEAR) + "(" +
				current.get(Calendar.HOUR_OF_DAY) + ":" +
				current.get(Calendar.MINUTE) + ":" +
				current.get(Calendar.SECOND) + ")";
	}

	@Override
	public void registerSectionEnding() {
		String entry = getTimeString() + "Section Ending.";

		out.println(entry);		
	}

	@Override
	public void registerSectionStarting() {
		String entry = getTimeString() + "Section Starting.";

		out.println(entry);
	}
}
