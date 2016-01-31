package banksys.persistence;

import java.io.File;
import java.io.Serializable;

import banksys.account.AbstractAccount;
import banksys.persistence.exception.AccountCreationException;
import banksys.persistence.exception.AccountDeletionException;
import banksys.persistence.exception.AccountNotFoundException;

public class BancoDAO implements IAccountRepository, Serializable {
	IAccountRepository contas;
	File arquivo;

	public BancoDAO(String path) {
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
		salvar();
	}

	@Override
	public void delete(String numero) throws AccountDeletionException  {
		contas.delete(numero);
		salvar();
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

	public void salvar() {
		try {
			Serializador.serializar(arquivo.getPath(), contas);
		} catch (Exception e) {
			System.out.println("ERROR: Impossivel salvar arquivo");
			e.printStackTrace();
		}

	}

}
