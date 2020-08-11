package br.com.serratec.javaFinal.usuarios;

public class Cliente extends Usuario {

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, String senha, String tipo, String agencia) {
		super(nome, cpf, senha, tipo, agencia);
	}

	@Override
	public String toString() {
		return " Nome: " + getNome() + " Tipo: " + getTipo() + " Cpf: "
				+ getCpf() + " Senha: " + getSenha()
				;
	}

}
