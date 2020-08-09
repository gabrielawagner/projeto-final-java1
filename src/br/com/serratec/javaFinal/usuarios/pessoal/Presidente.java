package br.com.serratec.javaFinal.usuarios.pessoal;

public class Presidente extends Diretor {
	public Presidente(String cpf, String senha, String cargo) {
		super(cpf, senha, cargo);
	}

	public Presidente(String cpf, String senha, String cargo, int agencia) {
		super(cpf, senha, cargo, agencia);
	}
}
