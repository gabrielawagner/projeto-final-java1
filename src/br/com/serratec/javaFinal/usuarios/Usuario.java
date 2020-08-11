package br.com.serratec.javaFinal.usuarios;

public abstract class Usuario implements Comparable<Usuario>{

	private String nome;
	private String cpf;
	private String senha;
	private String tipo;
	private String agencia;
	private boolean temSeguro = false;

	public Usuario(String nome, String cpf, String senha, String tipo, String agencia) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.tipo = tipo;
		this.agencia = agencia;
	}
	
	public Usuario() {
		super();
	}

	@Override
    public int compareTo(Usuario u) {
        return this.getNome().compareTo(u.getNome());
    }
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
}
