package br.com.serratec.javaFinal.usuarios;

public class Presidente extends Gerente {
	public Presidente() {
		super();
	}

	public Presidente(String nome, String cpf, String senha, String tipo, String agencia) {
		super(nome, cpf, senha, tipo, agencia);
	}
}
