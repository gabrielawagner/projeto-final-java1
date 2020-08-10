package br.com.serratec.javaFinal.usuarios.pessoal;

import br.com.serratec.javaFinal.usuarios.Usuario;

public abstract class Funcionario extends Usuario {

	public Funcionario() {
	}

	public Funcionario(String nome, String cpf, String senha, String tipo) {
		super(nome, cpf, senha, tipo);
	}

}
