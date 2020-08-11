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
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.enums.TiposContas;
import br.com.serratec.javaFinal.enums.TiposUsuarios;
import br.com.serratec.javaFinal.usuarios.Cliente;
import br.com.serratec.javaFinal.usuarios.Diretor;
import br.com.serratec.javaFinal.usuarios.Gerente;
import br.com.serratec.javaFinal.usuarios.Presidente;
import br.com.serratec.javaFinal.usuarios.Usuario;

public class LerArquivo {

	public void populaArrays(ArrayList<Usuario> usuarios, ArrayList<Conta> contas) throws IOException {

		FileReader fr = new FileReader("./usuarioReader.txt");
		BufferedReader buffRead = new BufferedReader(fr);

		String linha = "";

		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				String[] split = linha.split(",");
				if (split[3].equalsIgnoreCase(TiposUsuarios.CLIENTE.name())) {
					Cliente c = new Cliente(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(c);
				} else if (split[3].equalsIgnoreCase(TiposUsuarios.GERENTE.name())) {
					Gerente g = new Gerente(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(g);
				} else if (split[3].equalsIgnoreCase(TiposUsuarios.DIRETOR.name())) {
					Diretor d = new Diretor(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(d);
				} else if (split[3].equalsIgnoreCase(TiposUsuarios.PRESIDENTE.name())) {
					Presidente p = new Presidente(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(p);
				} else if (split[4].equals(TiposContas.CORRENTE.name())) {
					ContaCorrente cc = new ContaCorrente(split[0], split[1], split[2],
							Double.parseDouble(split[3]));
					contas.add(cc);
				} else if (split[4].equals(TiposContas.POUPANCA.name())) {
					ContaPoupanca cp = new ContaPoupanca(split[0], split[1], split[2], Double.parseDouble(split[3]));
					contas.add(cp);
				}
			} else
				break;
		}
		buffRead.close();
	}

	public static void escritor(String path) throws IOException {
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "";
		Scanner in = new Scanner(System.in);
		System.out.println("Escreva algo: ");
		linha = in.nextLine();
		buffWrite.append(linha + "\n");
		buffWrite.close();
		in.close();
	}
}