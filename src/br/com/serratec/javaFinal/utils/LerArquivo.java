package br.com.serratec.javaFinal.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.serratec.javaFinal.usuarios.Cliente;
import br.com.serratec.javaFinal.usuarios.TiposUsuarios;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.usuarios.pessoal.Diretor;
import br.com.serratec.javaFinal.usuarios.pessoal.Gerente;
import br.com.serratec.javaFinal.usuarios.pessoal.Presidente;

public class LerArquivo {
//		while (arquivoDeUsuarios.hasNext()) {
//			usuarios.add(arquivoDeUsuarios.nextLine());
//		}
//		
//		arquivoDeUsuarios.close();

//		FileWriter writer = new FileWriter(f);
//		for (String s : usuarios) {
//			writer.write(s + "\n");
//		}
		// writer.close();
	
	
	public ArrayList<Usuario> carregaArrayUsuarios() throws IOException{
		File f = new File(System.getProperty("user.dir") + "\\src\\br\\com\\serratec\\javaFinal\\utils\\usuarios.txt");
		if (!f.exists()) {
			f.createNewFile();
		}
		FileReader fr = new FileReader(
				System.getProperty("user.dir") + "\\src\\br\\com\\serratec\\javaFinal\\utils\\usuarios.txt");
		BufferedReader buffRead = new BufferedReader(fr);
		ArrayList<Usuario> lista = new ArrayList<>();
		String linha = "";
		Usuario usuario = null;

		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				String[] split = linha.split(",");
				if (split[3].equalsIgnoreCase(TiposUsuarios.CLIENTE.name())) {
					usuario = new Cliente(split[0], split[1], split[2], split[3]);
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
}