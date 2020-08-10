package br.com.serratec.javaFinal.usuarios.pessoal;

public class Diretor extends Gerente {

	public Diretor() {
		super();
	}

	public Diretor(String nome, String cpf, String senha, String tipo, String agencia) {
		super(nome, cpf, senha, tipo, agencia);
	}

	@Override
	public String toString() {
		return "getAgencia(): " + getAgencia() + "\ntoString(): " + super.toString() + "\ngetTipo(): " + getTipo()
				+ "\nisTemSeguro(): " + isTemSeguro() + "\ngetNome(): " + getNome() + "\ngetCpf(): " + getCpf()
				+ "\ngetSenha(): " + getSenha();
	}
	
}
