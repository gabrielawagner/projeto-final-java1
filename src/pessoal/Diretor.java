package pessoal;

public class Diretor extends Gerente {
	public Diretor(String cpf, String senha, String cargo) {
		super(cpf, senha, cargo);
	}

	public Diretor(String cpf, String senha, String cargo, int agencia) {
		super(cpf, senha, cargo, agencia);
	}
}
