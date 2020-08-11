package br.com.serratec.javaFinal.usuarios;

public abstract class Funcionario extends Usuario {

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String cpf, String senha, String tipo, String agencia) {
		super(nome, cpf, senha, tipo, agencia);
	}

}
