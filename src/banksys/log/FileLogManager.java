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
	public void registerAccountRemoval(String number) {
		String entry = getTimeString() + " account " + number + " removed.";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountCredit(String number, double amount) {
		String entry = getTimeString() + " credited " + amount + " in account " + number + ".";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountDebit(String number, double amount) {
		String entry = getTimeString() + " debited " + amount + " from account " + number + ".";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountTranfer(String originNumber, String destinyNumber, double amount) {
		String entry = getTimeString() + " transfered " + amount + 
					   " from account " + originNumber + 
					   " to account " + destinyNumber + ".";
		
		out.println(entry);	
	}

	@Override
	public void registerAccountInterestEarning(String number) {
		String entry = getTimeString() + " account " + number + 
					   " has earned interest.";
	
		out.println(entry);	
	}

	@Override
	public void registerAccountBonusEarning(String number) {
		String entry = getTimeString() + " account " + number + 
				   " has earned bonus.";

		out.println(entry);	
	}
	
	private String getTimeString() {
		Calendar current = Calendar.getInstance();
		
		return  current.get(Calendar.DAY_OF_MONTH) + "-" +
				current.get(Calendar.MONTH) + "-" +
				current.get(Calendar.YEAR) + "(" +
				current.get(Calendar.HOUR_OF_DAY) + "-" +
				current.get(Calendar.MINUTE) + "-" +
				current.get(Calendar.SECOND) + ")";
	}

	@Override
	public void registerSectionEnding() {
		String entry = getTimeString() + " Section Ending.";

		out.println(entry);		
	}

	@Override
	public void registerSectionStarting() {
		String entry = getTimeString() + " Section Starting.";

		out.println(entry);
	}
}
