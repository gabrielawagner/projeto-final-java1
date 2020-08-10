package br.com.serratec.javaFinal.usuarios.pessoal;

public class Presidente extends Diretor {
	public Presidente() {
		super();
	}

	public Presidente(String nome, String cpf, String senha, String tipo, String agencia) {
		super(nome, cpf, senha, tipo, agencia);
	}
}
