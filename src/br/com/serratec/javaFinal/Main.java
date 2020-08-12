package br.com.serratec.javaFinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.menus.Menu;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.ArquivoUtils;
import br.com.serratec.javaFinal.utils.LimpaTela;

/*
 *TODO Adicionar limpa tela no voltar do cliente
 *TODO Resolver formatacao do saque em cliente\conta corrente 
 *TODO Resolver formatacao do deposito em cliente\conta corrente 
 *TODO Resolver formatacao do saldo em relatorio cliente
 *TODO Colocar quantidade transaçoes na tributacao 
 *TODO arredondar rendimento poupanca 
 *
 * */

public class Main {
	public static void main(String[] args) throws IOException {
		// LerArquivo.escritor("./teste.txt");
		LimpaTela.limpaConsole();
		Scanner input = new Scanner(System.in);
		ArquivoUtils la = new ArquivoUtils();

		System.out.print("Cpf: ");
		String cpf = input.nextLine();
		System.out.print("Senha: ");
		String senha = input.nextLine();
		LimpaTela.limpaConsole();
		
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Conta> contas = new ArrayList<>();

		la.populaArrays(usuarios, contas);
		
		Collections.sort(usuarios);
		
		Menu menu = new Menu();

		for (Usuario usuario : usuarios) {
			if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
				for (Conta conta : contas) {
					if (conta.getCpfTitular().equals(usuario.getCpf())) {
						menu.principal(usuario, conta, usuarios, contas);
					}
				}
			}
		}

	}

}
