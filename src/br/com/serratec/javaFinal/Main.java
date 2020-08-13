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
import br.com.serratec.javaFinal.utils.Utils;

/*
 * */

public class Main {
	private static Object usuarios;
	private static Object contas;
	private static Object usuario;
	private static Object conta;

	public static void main(String[] args) throws IOException {
		// LerArquivo.escritor("./teste.txt");
		login();
	}
	
	public static void login() throws IOException {
		LimpaTela.limpaConsole();
		Scanner input = new Scanner(System.in);
		ArquivoUtils la = new ArquivoUtils();
		do {
		System.out.println("\r\n" +
                Utils.centraliza() + "----------------------------------------------------------------------------\r\n" +
                Utils.centraliza() + "██████╗░░█████╗░███╗░░██╗░█████╗░░█████╗░  ██████╗░███████╗████████╗░█████╗░\r\n" +
                Utils.centraliza() + "██╔══██╗██╔══██╗████╗░██║██╔══██╗██╔══██╗  ██╔══██╗██╔════╝╚══██╔══╝██╔══██╗\r\n" +
                Utils.centraliza() + "██████╦╝███████║██╔██╗██║██║░░╚═╝██║░░██║  ██████╦╝█████╗░░░░░██║░░░███████║\r\n" +
                Utils.centraliza() + "██╔══██╗██╔══██║██║╚████║██║░░██╗██║░░██║  ██╔══██╗██╔══╝░░░░░██║░░░██╔══██║\r\n" +
                Utils.centraliza() + "██████╦╝██║░░██║██║░╚███║╚█████╔╝╚█████╔╝  ██████╦╝███████╗░░░██║░░░██║░░██║\r\n" +
                Utils.centraliza() + "╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝░╚════╝░░╚════╝░  ╚═════╝░╚══════╝░░░╚═╝░░░╚═╝░░╚═╝\r\n" +
                Utils.centraliza() + "----------------------------------------------------------------------------");
		
		System.out.print(Utils.centraliza() + "CPF: ");
		String cpf = input.nextLine();
		System.out.print(Utils.centraliza() + "SENHA: ");
		String senha = input.nextLine();
		LimpaTela.limpaConsole();
		
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Conta> contas = new ArrayList<>();

		la.populaArrays(usuarios, contas);
		Menu menu = new Menu();
		
		for (Usuario usuario : usuarios) {
			if (usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
				for (Conta conta : contas) {
					if (conta.getCpfTitular().equals(usuario.getCpf())) {//TODO lanÃ§ar Exception Usuario Not Found
						System.out.println("BEM VINDO(A), "+ usuario.getNome().toUpperCase() + ".");
						menu.principal(usuario, conta, usuarios, contas);
					}
				}
			}
		}
		if (usuario == null && conta == null) {
			System.out.println(Utils.centraliza()+"CPF OU SENHA INCORRETOS, TENTE NOVAMENTE:");
		}
		}while (usuarios == null && contas == null);
	}
}