package br.com.serratec.javaFinal.usuarios;

public class Gerente extends Funcionario {

	public Gerente() {
	}

	public Gerente(String nome, String cpf, String senha, String tipo, String agencia) {
		super(nome, cpf, senha, tipo, agencia);
	}

}
