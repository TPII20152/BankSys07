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

import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;

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

		backgroundColor = new Color(255, 229, 20);
		foregroundColor = new Color(2, 92, 168);
		textColor = new Color(255, 255, 255);

		defaultFont = new Font(Font.SANS_SERIF, Font.BOLD, 24);
		defaultTextFont = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 16);

		buttonWidth = 150;

		setTitle("Terminal de Auto-Atendimento");
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

		JLabel newAccountLabel = new JLabel("CADASTRAR CONTA");
		newAccountLabel.setFont(defaultTextFont);
		newAccountLabel.setForeground(foregroundColor);
		JLabel removeAccountLabel = new JLabel("REMOVER CONTA");
		removeAccountLabel.setFont(defaultTextFont);
		removeAccountLabel.setForeground(foregroundColor);
		JLabel depositLabel = new JLabel("FAZER DEPÓSITO");
		depositLabel.setFont(defaultTextFont);
		depositLabel.setForeground(foregroundColor);
		JLabel withdrawLabel = new JLabel("FAZER SAQUE");
		withdrawLabel.setFont(defaultTextFont);
		withdrawLabel.setForeground(foregroundColor);
		JLabel transferLabel = new JLabel("FAZER TRANFERÊNCIA");
		transferLabel.setFont(defaultTextFont);
		transferLabel.setForeground(foregroundColor);
		JLabel balanceLabel = new JLabel("VERIFICAR SALDO");
		balanceLabel.setFont(defaultTextFont);
		balanceLabel.setForeground(foregroundColor);
		JLabel earningInterestLabel = new JLabel("RENDER JUROS");
		earningInterestLabel.setFont(defaultTextFont);
		earningInterestLabel.setForeground(foregroundColor);
		JLabel earningBonusLabel = new JLabel("RENDER BÓNUS");
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

		JLabel welcomingLabel = new JLabel("BEM VINDO AO BANCO DO BRASIL");
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
		southBtn.setText("SAIR");
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
		String accountNumber = JOptionPane.showInputDialog("Escreva o número da conta");

		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				bank.doEarnBonus(accountNumber);
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
			} catch (BankTransactionException bte) {
				JOptionPane.showMessageDialog(null, "ERRO: " + bte.getMessage());
			}
		}
	}

	protected void earningInterest() {
		String accountNumber = JOptionPane.showInputDialog("Escreva o número da conta");

		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				bank.doEarnInterest(accountNumber);
				JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
			} catch (BankTransactionException bte) {
				JOptionPane.showMessageDialog(null, "ERRO: " + bte.getMessage());
			}
		}
	}

	protected void balance() {
		String accountNumber = JOptionPane.showInputDialog("Escreva o número da conta");

		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				NumberFormat f = NumberFormat.getInstance();
				f.setMinimumFractionDigits(2);
				f.setMaximumFractionDigits(2);

				double balance = bank.getBalance(accountNumber);
				JOptionPane.showMessageDialog(null, "Seu saldo é de R$ " + f.format(balance),
						"Saldo da conta " + accountNumber, JOptionPane.INFORMATION_MESSAGE);
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
			} catch (BankTransactionException bte) {
				JOptionPane.showMessageDialog(null, "ERRO: " + bte.getMessage());
			}
		}
	}

	protected void transfer() {
		String originAccountNumber = JOptionPane.showInputDialog("Escreva o número da conta de origem");
		if (originAccountNumber != null) {
			String destinyAccountNumber = JOptionPane.showInputDialog("Escreva o número da conta de origem");
			if (destinyAccountNumber != null) {
				String valueStr = JOptionPane.showInputDialog("Escreva o valor a ser transferido.");
				if (valueStr != null) {
					valueStr = valueStr.replace(',', '.');
					try {
						Integer.parseInt(originAccountNumber);
						Integer.parseInt(destinyAccountNumber);
						double value = Double.parseDouble(valueStr);

						bank.doTransfer(originAccountNumber, destinyAccountNumber, value);
						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
					} catch (BankTransactionException bte) {
						JOptionPane.showMessageDialog(null, "ERRO: " + bte.getMessage());
					}
				}
			}
		}
	}

	protected void withdraw() {
		String accountNumber = JOptionPane.showInputDialog("Escreva o número da conta");
		if (accountNumber != null) {
			String valueStr = JOptionPane.showInputDialog("Escreva o valor a ser sacado.");
			valueStr = valueStr.replace(',', '.');
			if (valueStr != null) {
				try {
					Integer.parseInt(accountNumber);
					double value = Double.parseDouble(valueStr);

					bank.doDebit(accountNumber, value);
					JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
				} catch (BankTransactionException bte) {
					JOptionPane.showMessageDialog(null, "ERRO: " + bte.getMessage());
				}
			}
		}
	}

	protected void deposit() {
		String accountNumber = JOptionPane.showInputDialog("Escreva o número da conta");
		if (accountNumber != null) {
			String valueStr = JOptionPane.showInputDialog("Escreva o valor a ser depositado.");
			valueStr = valueStr.replace(',', '.');
			if (valueStr != null) {
				try {
					Integer.parseInt(accountNumber);
					double value = Double.parseDouble(valueStr);

					banco.creditar(accountNumber, value);
					JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
				} catch (TNRException tnre) {
					JOptionPane.showMessageDialog(null, "ERRO: " + tnre.getMessage());
				}
			}
		}
	}

	protected void removeAccount() {
		String accountNumber = JOptionPane.showInputDialog("Escreva o número da conta");
		if (accountNumber != null) {
			try {
				Integer.parseInt(accountNumber);

				try {
					banco.remover(accountNumber);
					JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
				} catch (CIException cie) {
					JOptionPane.showMessageDialog(null, "ERRO: " + cie.getMessage());
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
			}
		}
	}

	protected void addNewAccount() {
		ContaAbstrata conta = null;

		String[] options = { "Comum", "Especial", "Poupança", "Imposto" };
		int accountType = JOptionPane.showOptionDialog(null, "Escolha o tipo de conta.", "Tipo de Conta",
				JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, 0);

		if (accountType >= 0) {
			String accountNumber = JOptionPane.showInputDialog("Escreva o número da conta");

			if (accountNumber != null) {
				try {
					Integer.parseInt(accountNumber);

					switch (accountType) {
					case 0:
						conta = new Conta(accountNumber);
						break;
					case 1:
						conta = new ContaEspecial(accountNumber);
						break;
					case 2:
						conta = new ContaPoupanca(accountNumber);
						break;
					case 3:
						conta = new ContaImposto(accountNumber);
						break;
					default:
						break;
					}

					try {
						banco.cadastrar(conta);
						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
					} catch (CEException cee) {
						JOptionPane.showMessageDialog(null, "ERRO: " + cee.getMessage());
					}

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "ERRO: Valor Inválido");
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
		new JanelaAtendimento(new BancoBrasil(new BancoDAO("banco.ser")));
	}
}
