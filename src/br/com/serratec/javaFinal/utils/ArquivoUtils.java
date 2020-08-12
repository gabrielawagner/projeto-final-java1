package br.com.serratec.javaFinal.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.enums.EnumContas;
import br.com.serratec.javaFinal.enums.EnumUsuarios;
import br.com.serratec.javaFinal.usuarios.Cliente;
import br.com.serratec.javaFinal.usuarios.Diretor;
import br.com.serratec.javaFinal.usuarios.Gerente;
import br.com.serratec.javaFinal.usuarios.Presidente;
import br.com.serratec.javaFinal.usuarios.Usuario;

public class ArquivoUtils {

	public void populaArrays(ArrayList<Usuario> usuarios, ArrayList<Conta> contas) throws IOException {

		FileReader fr = new FileReader("./usuarioReader.txt");
		BufferedReader buffRead = new BufferedReader(fr);

		String linha = "";

		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				String[] split = linha.split(",");
				if (split[3].equalsIgnoreCase(EnumUsuarios.CLIENTE.name())) {
					Cliente c = new Cliente(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(c);
				} else if (split[3].equalsIgnoreCase(EnumUsuarios.GERENTE.name())) {
					Gerente g = new Gerente(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(g);
				} else if (split[3].equalsIgnoreCase(EnumUsuarios.DIRETOR.name())) {
					Diretor d = new Diretor(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(d);
				} else if (split[3].equalsIgnoreCase(EnumUsuarios.PRESIDENTE.name())) {
					Presidente p = new Presidente(split[0], split[1], split[2], split[3], split[4]);
					usuarios.add(p);
				} else if (split[4].equals(EnumContas.CORRENTE.name())) {
					ContaCorrente cc = new ContaCorrente(split[0], split[1], split[2], Double.parseDouble(split[3]), split[4]);
					contas.add(cc);
				} else if (split[4].equals(EnumContas.POUPANCA.name())) {
					ContaPoupanca cp = new ContaPoupanca(split[0], split[1], split[2], Double.parseDouble(split[3]),split[4]);
					contas.add(cp);
				}
			} else
				break;
		}
		buffRead.close();
	}

	public static void escritor(String path, Usuario usuario, Conta conta) throws IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();
		
		path += simpleDateFormat.format(data) + usuario.getNome() + ".bancoBeta";
		File f = new File(path);
		
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "**********Saldo**********";
		buffWrite.append(linha + "\n");

		if (usuario.getTipo().equals(EnumUsuarios.CLIENTE.name())) {
			linha = "" + ((Cliente) usuario).getAgencia();
			buffWrite.append(linha + "\n");
			linha = "" + ((Cliente) usuario).getNome();
			buffWrite.append(linha + "\n");
			linha = "" + ((Cliente) usuario).getAgencia();
			buffWrite.append(linha + "\n");
			linha = "" + conta.getSaldo();
			buffWrite.append(linha + "\n");
		}
		if (usuario.getTipo().equals(EnumUsuarios.GERENTE.name())) {
			((Cliente) usuario).getAgencia();
		}

		linha = "**********Fim do Saldo**********";
		buffWrite.append(linha + "\n");

		buffWrite.close();
	}
}