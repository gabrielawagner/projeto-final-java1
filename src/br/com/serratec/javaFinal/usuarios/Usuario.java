package br.com.serratec.javaFinal.usuarios;

public abstract class Usuario {

	private String nome;
	private String cpf;
	private String senha;
	private boolean temSeguro;
	private String cargo;

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public boolean isTemSeguro() {
		return temSeguro;
	}

	public void setTemSeguro(boolean temSeguro) {
		this.temSeguro = temSeguro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Usuario(String nome, String cpf, String senha, boolean temSeguro, String cargo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.temSeguro = temSeguro;
		this.cargo = cargo;
	}

	public Usuario() {
		super();
	}

}
