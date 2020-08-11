package br.com.serratec.javaFinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.enums.TiposContas;
import br.com.serratec.javaFinal.enums.TiposUsuarios;
import br.com.serratec.javaFinal.menus.Menu;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.LerArquivo;
import br.com.serratec.javaFinal.utils.LimpaTela;

public class Main {
	public static void main(String[] args) throws IOException {
		// LerArquivo.escritor("./teste.txt");
		LimpaTela.limpaConsole();
		Scanner input = new Scanner(System.in);
		LerArquivo la = new LerArquivo();

		System.out.print("Cpf: ");
		String cpf = "4";
		//= input.nextLine();
		System.out.print("Senha: ");
		String senha = "senha";
		//= input.nextLine();
		LimpaTela.limpaConsole();

		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Conta> contas = new ArrayList<>();

		la.populaArrays(usuarios, contas);
		Collections.sort(usuarios);
		
		ContaCorrente cc = null;
		ContaPoupanca cp = null;
		Menu menu = new Menu();

		for (Usuario usuario : usuarios) {
			if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
				for (Conta conta : contas) {
					if (conta.getCpfTitular().equals(usuario.getCpf())) {
						menu.setConta(conta);
						menu.setUsuario(usuario);
//						if (conta.getTipoConta().equals(TiposContas.CORRENTE.name())) {//TODO TENTAR DEPOIS
//							cc = (ContaCorrente) conta;
//							menu.setConta(conta);
//							menu.setUsuario(usuario);
//						}
//						if (conta.getTipoConta().equals(TiposContas.POUPANCA.name())) {
//							cp = (ContaPoupanca) conta;
//							menu.setConta(conta);
//							menu.setUsuario(usuario);
//						}
					}
				}
				menu.principal(usuarios, contas);
			}
		}

	}

}
