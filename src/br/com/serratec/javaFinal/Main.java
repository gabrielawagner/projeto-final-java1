package br.com.serratec.javaFinal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;
import br.com.serratec.javaFinal.enums.TiposUsuarios;
import br.com.serratec.javaFinal.menus.Menu;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.LerArquivo;
import br.com.serratec.javaFinal.utils.LimpaTela;

public class Main {
	public static void main(String[] args) throws IOException {
		//LerArquivo.escritor("./teste.txt");
		Scanner input = new Scanner(System.in);
		LerArquivo la = new LerArquivo();
		
		ContaCorrente cc = new ContaCorrente(1, "cpf", 1000);
		
		
		System.out.print("Cpf: ");
		String cpf = input.nextLine();
		System.out.print("Senha: ");
		String senha = input.nextLine();
		LimpaTela.clearConsole();
		ArrayList<Usuario> usuarios = la.carregaArrayUsuarios();
		
		for (Usuario usuario : usuarios) {
			if(usuario.getCpf().equals(cpf) && usuario.getSenha().equals(senha)) {
				Menu menu = new Menu(usuario, cc);
				String tipo = usuario.getTipo();
				
				if(tipo.equals(TiposUsuarios.CLIENTE.name())) {
					menu.cliente(usuario);
				} else if (tipo.equals(TiposUsuarios.GERENTE)) {
					System.out.println("Qual menu? [1]Cliente [2]Gerente");//TODO resolver recursividade
					int opcao = input.nextInt();
					//menu.gerente(usuario);
				}
				
			}
		}
		
		
	}

}
