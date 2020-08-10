package br.com.serratec.javaFinal.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lerArquivo {

	public static void main(String[] args) throws IOException {
		File f = new File(System.getProperty("user.dir")+"\\src\\br\\com\\serratec\\javaFinal\\utils\\teste.txt");
		if(!f.exists()) {
			f.createNewFile();
		}
		Scanner arquivoDeUsuarios = new Scanner(f);
		List<String> usuarios = new ArrayList<String>();

		while (arquivoDeUsuarios.hasNext()) {
			usuarios.add(arquivoDeUsuarios.nextLine());
		}
		arquivoDeUsuarios.close();
		
		String novo = "teste, 123123, 12312312";

		usuarios.add(novo);
		usuarios.add(novo);
		usuarios.add(novo);
		usuarios.add(novo);
		usuarios.add(novo);
		
		FileWriter writer = new FileWriter(f);
		for (String s : usuarios) {
			writer.write(s + "\n");
		}
		writer.close();
	}
}