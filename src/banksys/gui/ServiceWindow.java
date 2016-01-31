package banksys.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.TaxAccount;
import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;
import banksys.log.FileLogManager;
import banksys.persistence.AccountVector;
import banksys.persistence.BankDAO;

public class ServiceWindow extends JFrame {

	BankController bank;

	JButton firstWestBtn;
	JButton secondWestBtn;
	JButton thirdWestBtn;
	JButton fourthWestBtn;
	JButton firstEastBtn;
	JButton secondEastBtn;
	JButton thirdEastBtn;
	JButton fourthEastBtn;
	JButton southBtn;

	ActionListener newAccountListener;
	ActionListener removeAccountListener;
	ActionListener depositListener;
	ActionListener withdrawListener;
	ActionListener transferListener;
	ActionListener balanceListener;
	ActionListener earningInterestListener;
	ActionListener earningBonusListener;
	ActionListener exitListener;
	ActionListener cancelListener;

	Color backgroundColor;
	Color foregroundColor;
	Color textColor;

	Font defaultFont;
	Font defaultTextFont;

	int buttonWidth;

	public ServiceWindow(BankController bank) {
		this.bank = bank;

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		backgroundColor = new Color(255, 20, 20);
		foregroundColor = new Color(255, 255, 255);
		textColor = new Color(255, 20, 20);

		defaultFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		defaultTextFont = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 16);

		buttonWidth = 150;

		setTitle("Self Service Terminal");
		setMinimumSize(new Dimension(800, 600));

		JPanel mainPane = new JPanel(new BorderLayout(15, 15));
		JPanel westPane = new JPanel(new GridLayout(4, 1, 1, 15));
		JPanel eastPane = new JPanel(new GridLayout(4, 1, 1, 15));
		JPanel southPane = new JPanel(new BorderLayout());
		JPanel northPane = new JPanel();
		JPanel centerPane = new JPanel(new BorderLayout(1, 1));

		instantiateListeners();

		firstWestBtn = new JButton();
		secondWestBtn = new JButton();
		thirdWestBtn = new JButton();
		fourthWestBtn = new JButton();
		firstEastBtn = new JButton();
		secondEastBtn = new JButton();
		thirdEastBtn = new JButton();
		fourthEastBtn = new JButton();
		southBtn = new JButton();

		setButtonInitialStatus();

		JLabel newAccountLabel = new JLabel("REGISTER ACCOUNT");
		newAccountLabel.setFont(defaultTextFont);
		newAccountLabel.setForeground(foregroundColor);
		JLabel removeAccountLabel = new JLabel("REMOVE ACCOUNT");
		removeAccountLabel.setFont(defaultTextFont);
		removeAccountLabel.setForeground(foregroundColor);
		JLabel depositLabel = new JLabel("DO DEPOSIT");
		depositLabel.setFont(defaultTextFont);
		depositLabel.setForeground(foregroundColor);
		JLabel withdrawLabel = new JLabel("DO WITHDRAW");
		withdrawLabel.setFont(defaultTextFont);
		withdrawLabel.setForeground(foregroundColor);
		JLabel transferLabel = new JLabel("DO TRANFER");
		transferLabel.setFont(defaultTextFont);
		transferLabel.setForeground(foregroundColor);
		JLabel balanceLabel = new JLabel("CHECK BALANCE");
		balanceLabel.setFont(defaultTextFont);
		balanceLabel.setForeground(foregroundColor);
		JLabel earningInterestLabel = new JLabel("EARN INTEREST");
		earningInterestLabel.setFont(defaultTextFont);
		earningInterestLabel.setForeground(foregroundColor);
		JLabel earningBonusLabel = new JLabel("EARN BONUS");
		earningBonusLabel.setFont(defaultTextFont);
		earningBonusLabel.setForeground(foregroundColor);

		centerPane.setBackground(backgroundColor);

		JPanel centerWestPane = new JPanel(new GridLayout(4, 1));
		centerWestPane.add(newAccountLabel);
		centerWestPane.add(depositLabel);
		centerWestPane.add(transferLabel);
		centerWestPane.add(earningInterestLabel);
		centerWestPane.setBackground(backgroundColor);
		centerPane.add(centerWestPane, BorderLayout.WEST);

		JPanel centerEastPane = new JPanel(new GridLayout(4, 1));
		centerEastPane.add(removeAccountLabel);
		centerEastPane.add(withdrawLabel);
		centerEastPane.add(balanceLabel);
		centerEastPane.add(earningBonusLabel);
		centerEastPane.setBackground(backgroundColor);
		centerPane.add(centerEastPane, BorderLayout.EAST);

		JLabel welcomingLabel = new JLabel("WELCOME TO SELF SERVICE TERMINAL");
		welcomingLabel.setFont(defaultFont);
		welcomingLabel.setForeground(foregroundColor);

		westPane.add(firstWestBtn);
		westPane.add(secondWestBtn);
		westPane.add(thirdWestBtn);
		westPane.add(fourthWestBtn);
		westPane.setBackground(backgroundColor);
		westPane.setPreferredSize(new Dimension(buttonWidth, 0));

		eastPane.add(firstEastBtn);
		eastPane.add(secondEastBtn);
		eastPane.add(thirdEastBtn);
		eastPane.add(fourthEastBtn);
		eastPane.setBackground(backgroundColor);
		eastPane.setPreferredSize(new Dimension(buttonWidth, 0));

		southBtn.setForeground(textColor);
		southPane.add(southBtn, BorderLayout.CENTER);
		southPane.setBackground(backgroundColor);
		southPane.setPreferredSize(new Dimension(800, 60));

		northPane.add(welcomingLabel);
		northPane.setBackground(backgroundColor);

		mainPane.add(westPane, BorderLayout.WEST);
		mainPane.add(eastPane, BorderLayout.EAST);
		mainPane.add(centerPane, BorderLayout.CENTER);
		mainPane.add(southPane, BorderLayout.SOUTH);
		mainPane.add(northPane, BorderLayout.NORTH);
		mainPane.setBackground(backgroundColor);

		JPanel externalPane = new JPanel(new BorderLayout(0, 0));
		JPanel bottomPane = new JPanel();
		JPanel topPane = new JPanel();
		JPanel leftPane = new JPanel();
		JPanel rightPane = new JPanel();
		bottomPane.setPreferredSize(new Dimension(830, 15));
		bottomPane.setBackground(backgroundColor);
		topPane.setPreferredSize(new Dimension(830, 15));
		topPane.setBackground(backgroundColor);
		leftPane.setPreferredSize(new Dimension(15, 630));
		leftPane.setBackground(backgroundColor);
		rightPane.setPreferredSize(new Dimension(15, 630));
		rightPane.setBackground(backgroundColor);
		externalPane.add(mainPane, BorderLayout.CENTER);
		externalPane.add(bottomPane, BorderLayout.SOUTH);
		externalPane.add(topPane, BorderLayout.NORTH);
		externalPane.add(leftPane, BorderLayout.WEST);
		externalPane.add(rightPane, BorderLayout.EAST);

		setContentPane(externalPane);

		setEnabled(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setButtonInitialStatus() {
		southBtn.setText("EXIT");
		southBtn.setFont(defaultTextFont);
		southBtn.setForeground(foregroundColor);

		firstWestBtn.addActionListener(newAccountListener);
		firstEastBtn.addActionListener(removeAccountListener);
		secondWestBtn.addActionListener(depositListener);
		secondEastBtn.addActionListener(withdrawListener);
		thirdWestBtn.addActionListener(transferListener);
		thirdEastBtn.addActionListener(balanceListener);
		fourthWestBtn.addActionListener(earningInterestListener);
		fourthEastBtn.addActionListener(earningBonusListener);
		southBtn.addActionListener(exitListener);
	}

	protected void earningBonus() {
		String accountNumber = JOptionPane.showInputDialog("Enter your account number.");

		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				bank.doEarnBonus(accountNumber);
				JOptionPane.showMessageDialog(null, "Operation Successful!");
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
			} catch (BankTransactionException bte) {
				JOptionPane.showMessageDialog(null, "ERRORR: " + bte.getMessage());
			}
		}
	}

	protected void earningInterest() {
		String accountNumber = JOptionPane.showInputDialog("Enter your account number.");

		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				bank.doEarnInterest(accountNumber);
				JOptionPane.showMessageDialog(null, "Operation Successful!");
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
			} catch (BankTransactionException bte) {
				JOptionPane.showMessageDialog(null, "ERROR: " + bte.getMessage());
			}
		}
	}

	protected void balance() {
		String accountNumber = JOptionPane.showInputDialog("Enter your account number.");

		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				NumberFormat f = NumberFormat.getInstance();
				f.setMinimumFractionDigits(2);
				f.setMaximumFractionDigits(2);

				double balance = bank.getBalance(accountNumber);
				JOptionPane.showMessageDialog(null, "Your balance is $ " + f.format(balance),
						"Balance of Account " + accountNumber, JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
			} catch (BankTransactionException bte) {
				JOptionPane.showMessageDialog(null, "ERROR: " + bte.getMessage());
			}
		}
	}

	protected void transfer() {
		String originAccountNumber = JOptionPane.showInputDialog("Enter the origin account number.");
		if (originAccountNumber != null) {
			String destinyAccountNumber = JOptionPane.showInputDialog("Enter the destiny account number.");
			if (destinyAccountNumber != null) {
				String valueStr = JOptionPane.showInputDialog("Enter the amount to be transfered.");
				if (valueStr != null) {
					valueStr = valueStr.replace(',', '.');
					try {
						Integer.parseInt(originAccountNumber);
						Integer.parseInt(destinyAccountNumber);
						double value = Double.parseDouble(valueStr);

						bank.doTransfer(originAccountNumber, destinyAccountNumber, value);
						JOptionPane.showMessageDialog(null, "Operation Successful!");
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
					} catch (BankTransactionException bte) {
						JOptionPane.showMessageDialog(null, "ERROR: " + bte.getMessage());
					}
				}
			}
		}
	}

	protected void withdraw() {
		String accountNumber = JOptionPane.showInputDialog("Enter your account number.");
		if (accountNumber != null) {
			String valueStr = JOptionPane.showInputDialog("Enter the amount to be withdraw.");
			valueStr = valueStr.replace(',', '.');
			if (valueStr != null) {
				try {
					Integer.parseInt(accountNumber);
					double value = Double.parseDouble(valueStr);

					bank.doDebit(accountNumber, value);
					JOptionPane.showMessageDialog(null, "Operation Successful!");
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
				} catch (BankTransactionException bte) {
					JOptionPane.showMessageDialog(null, "ERROR: " + bte.getMessage());
				}
			}
		}
	}

	protected void deposit() {
		String accountNumber = JOptionPane.showInputDialog("Enter your account number.");
		if (accountNumber != null) {
			String valueStr = JOptionPane.showInputDialog("Enter the value to be deposited.");
			valueStr = valueStr.replace(',', '.');
			if (valueStr != null) {
				try {
					Integer.parseInt(accountNumber);
					double value = Double.parseDouble(valueStr);

					bank.doCredit(accountNumber, value);
					JOptionPane.showMessageDialog(null, "Operation Successful!");
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
				} catch (BankTransactionException bte) {
					JOptionPane.showMessageDialog(null, "ERROR: " + bte.getMessage());
				}
			}
		}
	}

	protected void removeAccount() {
		String accountNumber = JOptionPane.showInputDialog("Enter your account number.");
		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				try {
					bank.removeAccount(accountNumber);
					JOptionPane.showMessageDialog(null, "Operation Successful!");
				} catch (BankTransactionException bte) {
					JOptionPane.showMessageDialog(null, "ERROR: " + bte.getMessage());
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
			}
		}
	}

	protected void addNewAccount() {
		AbstractAccount account = null;

		String[] options = { "Ordinary", "Special", "Saving", "Tax" };
		int accountType = JOptionPane.showOptionDialog(null, "Choose your account type.", "Account Type",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);

		if (accountType >= 0) {
			String accountNumber = JOptionPane.showInputDialog("Enter your account number.");

			if (accountNumber != null) {
				try {
					Integer.parseInt(accountNumber);

					switch (accountType) {
					case 0:
						account = new OrdinaryAccount(accountNumber);
						break;
					case 1:
						account = new SpecialAccount(accountNumber);
						break;
					case 2:
						account = new SavingsAccount(accountNumber);
						break;
					case 3:
						account = new TaxAccount(accountNumber);
						break;
					default:
						break;
					}

					try {
						bank.addAccount(account);
						JOptionPane.showMessageDialog(null, "Operation Successful!");
					} catch (BankTransactionException bte) {
						JOptionPane.showMessageDialog(null, "ERROR: " + bte.getMessage());
					}

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "ERRORR: Invalid Value");
				}
			}
		}
	}

	private void instantiateListeners() {
		newAccountListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addNewAccount();
			}
		};

		removeAccountListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeAccount();
			}
		};

		depositListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit();
			}
		};

		withdrawListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdraw();
			}
		};

		transferListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transfer();
			}
		};

		balanceListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				balance();
			}
		};

		earningInterestListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				earningInterest();
			}
		};

		earningBonusListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				earningBonus();
			}
		};

		exitListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		};
	}

	public static void main(String[] args) {
		new ServiceWindow(new BankController(new BankDAO("banco.dat"), new FileLogManager()));
	}
}
