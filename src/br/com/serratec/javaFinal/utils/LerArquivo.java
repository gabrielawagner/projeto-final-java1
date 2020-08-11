package br.com.serratec.javaFinal.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;
import br.com.serratec.javaFinal.enums.TiposUsuarios;
import br.com.serratec.javaFinal.usuarios.Cliente;
import br.com.serratec.javaFinal.usuarios.Diretor;
import br.com.serratec.javaFinal.usuarios.Gerente;
import br.com.serratec.javaFinal.usuarios.Presidente;
import br.com.serratec.javaFinal.usuarios.Usuario;

public class LerArquivo {

	
	public ArrayList<Usuario> carregaArrayUsuarios() throws IOException{
		FileReader fr = new FileReader("./usuarioReader.txt");
		BufferedReader buffRead = new BufferedReader(fr);
		ArrayList<Usuario> lista = new ArrayList<>();
		String linha = "";
		Usuario usuario = null;
		Conta conta = null;
		
		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				String[] split = linha.split(",");
				if (split[3].equalsIgnoreCase(TiposUsuarios.CLIENTE.name())) {
					usuario = new Cliente(split[0], split[1], split[2], split[3]);
					if (split[6].equalsIgnoreCase("ContaCorrente")) {
						conta = new ContaCorrente(Integer.parseInt(split[4]), split[1], Double.parseDouble(split[5]));
					}
					lista.add(usuario);
				} else if (split[3].equalsIgnoreCase(TiposUsuarios.GERENTE.name())) {
					usuario = new Gerente(split[0], split[1], split[2], split[3], split[4]);
					lista.add(usuario);
				} else if (split[3].equalsIgnoreCase(TiposUsuarios.DIRETOR.name())) {
					usuario = new Diretor(split[0], split[1], split[2], split[3], split[4]);
					lista.add(usuario);
				}else if (split[3].equalsIgnoreCase(TiposUsuarios.PRESIDENTE.name())) {
					usuario = new Presidente(split[0], split[1], split[2], split[3], split[4]);
					lista.add(usuario);
				}
			} else
				break;
		}
		buffRead.close();
		return lista;
	}
	
	public static void escritor(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		linha = in.nextLine();
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}
}