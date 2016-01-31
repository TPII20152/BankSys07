package banksys.persistence;

import java.io.File;
import java.io.Serializable;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class BankDAO implements IAccountRepository, Serializable {
	IAccountRepository contas;
	File arquivo;

	public BankDAO(String path) {
		this.arquivo = new File(path);

		if (this.arquivo.exists()) {
			try {
				contas = (AccountVector) Descerializador.descerializar(path);
			} catch (Exception e) {
				System.out.println("ERROR: Impossivel abrir o arquivo");
				e.printStackTrace();
			}
		} else {
			contas = new AccountVector();
		}
	}

	@Override
	public void create(AbstractAccount conta) throws AccountCreationException {
		contas.create(conta);
		save();
	}

	@Override
	public void delete(String numero) throws AccountDeletionException  {
		contas.delete(numero);
		save();
	}

	@Override
	public AbstractAccount retrieve(String numero) throws AccountNotFoundException {
		return contas.retrieve(numero);
	}

	@Override
	public AbstractAccount[] list() {
		return contas.list();
	}

	@Override
	public int numberOfAccounts() {
		return contas.numberOfAccounts();
	}

	@Override
	public void save() {
		try {
			Serializador.serializar(arquivo.getPath(), contas);
		} catch (Exception e) {
			System.out.println("ERROR: Impossivel salvar arquivo");
			e.printStackTrace();
		}

	}

}
