package br.com.serratec.javaFinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.exceptions.UsuarioNaoCadastradoException;
import br.com.serratec.javaFinal.menus.Menu;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.ArquivoUtils;
import br.com.serratec.javaFinal.utils.LimpaTela;
import br.com.serratec.javaFinal.utils.Utils;

public class Main {
	public static void main(String[] args) throws IOException {
<<<<<<< HEAD
		try {
=======
>>>>>>> 1170ff63d8b3afa19932e513dce2b9c0412fe6fb
		login();
		} catch (UsuarioNaoCadastradoException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void login() throws IOException {
<<<<<<< HEAD
		for (int tentativaLogin = 0; tentativaLogin < 3; tentativaLogin++) {
			LimpaTela.limpaConsole();
			Scanner input = new Scanner(System.in);
			ArquivoUtils la = new ArquivoUtils();
=======
		LimpaTela.limpaConsole();
		Scanner input = new Scanner(System.in);
		ArquivoUtils la = new ArquivoUtils();
		do {
>>>>>>> 1170ff63d8b3afa19932e513dce2b9c0412fe6fb
			System.out.println("\r\n" +
	                Utils.centraliza() + "----------------------------------------------------------------------------\r\n" +
	                Utils.centraliza() + "██████╗░░█████╗░███╗░░██╗░█████╗░░█████╗░  ██████╗░███████╗████████╗░█████╗░\r\n" +
	                Utils.centraliza() + "██╔══██╗██╔══██╗████╗░██║██╔══██╗██╔══██╗  ██╔══██╗██╔════╝╚══██╔══╝██╔══██╗\r\n" +
	                Utils.centraliza() + "██████╦╝███████║██╔██╗██║██║░░╚═╝██║░░██║  ██████╦╝█████╗░░░░░██║░░░███████║\r\n" +
	                Utils.centraliza() + "██╔══██╗██╔══██║██║╚████║██║░░██╗██║░░██║  ██╔══██╗██╔══╝░░░░░██║░░░██╔══██║\r\n" +
	                Utils.centraliza() + "██████╦╝██║░░██║██║░╚███║╚█████╔╝╚█████╔╝  ██████╦╝███████╗░░░██║░░░██║░░██║\r\n" +
	                Utils.centraliza() + "╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝░╚════╝░░╚════╝░  ╚═════╝░╚══════╝░░░╚═╝░░░╚═╝░░╚═╝\r\n" +
<<<<<<< HEAD
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
						if (conta.getCpfTitular().equals(usuario.getCpf())) {
							System.out.println("BEM VINDO(A), " + usuario.getNome().toUpperCase() + ".");
							menu.principal(usuario, conta, usuarios, contas);
							break;
						}
=======
	                Utils.centraliza() + "----------------------------------------------------------------------------\r\n");
		
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
					if (conta.getCpfTitular().equals(usuario.getCpf())) {
						System.out.println("BEM VINDO(A), "+ usuario.getNome().toUpperCase() + ".");
						menu.principal(usuario, conta, usuarios, contas);
>>>>>>> 1170ff63d8b3afa19932e513dce2b9c0412fe6fb
					}
				}
			}
			
			if(tentativaLogin == 2) {
				input.close();
				throw new UsuarioNaoCadastradoException("usuario não encontrado entre em contato com o administrador!".toUpperCase());
			}
			System.out.println(Utils.centraliza() + "CPF OU SENHA INCORRETOS, TENTE NOVAMENTE.");
		}
<<<<<<< HEAD
=======
		if (usuario == null && conta == null) {
			System.out.println(Utils.centraliza()+"CPF OU SENHA INCORRETOS, TENTE NOVAMENTE:");
		}
		input.close();
		}while (usuarios == null && contas == null);
>>>>>>> 1170ff63d8b3afa19932e513dce2b9c0412fe6fb
	}
}