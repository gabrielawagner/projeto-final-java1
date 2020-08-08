package br.com.serratec.javaFinal.pessoal;

import br.com.serratec.javaFinal.usuarios.Usuario;

public abstract class Funcionario extends Usuario {

	private String cpf;
	private String senha;

	public Funcionario() {
	}

	public Funcionario(String cpf, String senha, String cargo) {
		this.cpf = cpf;
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
