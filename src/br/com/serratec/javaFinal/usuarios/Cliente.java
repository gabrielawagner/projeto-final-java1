package br.com.serratec.javaFinal.usuarios;

public class Cliente extends Usuario {

	public Cliente(String nome, String cpf, String senha, String tipo) {
		super(nome, cpf, senha, tipo);
	}

	@Override
	public String toString() {
		return " Nome: " + getNome() + " Tipo: " + getTipo() + " Cpf: "
				+ getCpf() + " Senha: " + getSenha()
				;
	}
	
	

}
