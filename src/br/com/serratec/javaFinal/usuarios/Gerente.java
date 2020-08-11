package br.com.serratec.javaFinal.usuarios;

public class Gerente extends Funcionario {

	private String agenciaResponsavel;

	public Gerente() {
	}

	public Gerente(String nome, String cpf, String senha, String tipo, String agencia) {
		super(nome, cpf, senha, tipo);
		this.agenciaResponsavel = agencia;
	}

	public String getAgencia() {
		return agenciaResponsavel;
	}

	public void setAgencia(String agencia) {
		this.agenciaResponsavel = agencia;
	}
	
	@Override
	public String toString() {
		return "agencia: " + agenciaResponsavel + " Tipo: " + getTipo() + " Nome: " + getNome() + " Cpf: "
				+ getCpf() + " Senha: " + getSenha();
	}

}
