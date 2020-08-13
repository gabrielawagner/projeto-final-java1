package br.com.serratec.javaFinal.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.contaBancaria.Tributos;
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
					ContaCorrente cc = new ContaCorrente(split[0], split[1], split[2], Double.parseDouble(split[3]),
							split[4]);
					contas.add(cc);
				} else if (split[4].equals(EnumContas.POUPANCA.name())) {
					ContaPoupanca cp = new ContaPoupanca(split[0], split[1], split[2], Double.parseDouble(split[3]),
							split[4]);
					contas.add(cp);
				}
			} else
				break;
		}
		buffRead.close();
	}

	public static void saldo(String path, Usuario usuario, Conta conta) throws IOException {
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "SALDO_" + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		String linha = "********************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "SALDO DA CONTA " + conta.getTipoConta();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "AGÊNCIA: " + usuario.getAgencia();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "CONTA: " + conta.getNumero();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "TITULAR: " + usuario.getNome();
		buffWrite.append(linha + "\n");
		linha = "********************************************";
		buffWrite.append(linha + "\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		linha = Utils.centraliza() + "SALDO EM " + sdf.format(data) + ": " + conta.getSaldo();
		buffWrite.append(linha + "\n");

		linha = "********************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void deposita(String path, Usuario usuario, Conta conta, double valor) throws IOException {
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "DEPOSITO_" + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		String linha = "********************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "TITULAR: " + usuario.getNome();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "AGÊNCIA: " + usuario.getAgencia();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "CONTA: " + conta.getNumero();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "VALOR DEPOSITADO: " + valor;
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "NOVO SALDO DA CONTA: " + conta.getSaldo();
		buffWrite.append(linha + "\n");
		linha = "********************************************";
		buffWrite.append(linha + "\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		linha = Utils.centraliza() + "DATA DA TRANSAÇÃO: " + sdf.format(data);
		buffWrite.append(linha + "\n");

		linha = "********************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void saque(String path, Usuario usuario, Conta conta, double valor) throws IOException {
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "SAQUE_" + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		String linha = "********************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "TITULAR: " + usuario.getNome();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "AGÊNCIA: " + usuario.getAgencia();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "CONTA: " + conta.getNumero();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "VALOR SACADO: " + valor;
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "NOVO SALDO DA CONTA: " + conta.getSaldo();
		buffWrite.append(linha + "\n");
		linha = "********************************************";
		buffWrite.append(linha + "\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		linha = Utils.centraliza() + "DATA DA TRANSAÇÃO: " + sdf.format(data);
		buffWrite.append(linha + "\n");

		linha = "********************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void transferencia(String path, Usuario usuario, Conta conta, Conta destino, double valor)
			throws IOException {
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "TRANSFERENCIA_" + formataData.format(data) + usuario.getNome() + ".bancoBeta";
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
		String linha = "******************************************************************************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "COMPROVANTE DE TRANSFERÊNCIA DE CONTA " + conta.getTipoConta() + " PARA CONTA "
				+ destino.getTipoConta();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "DADOS DA CONTA DEBITADA:";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "AGÊNCIA: " + usuario.getAgencia();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "CONTA: " + conta.getNumero();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "DADOS DA CONTA CREDITADA:";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "AGÊNCIA: " + usuario.getAgencia();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "CONTA: " + destino.getNumero();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "VALOR: " + valor;
		buffWrite.append(linha + "\n");
		linha = "******************************************************************************************************";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "TRANSFERÊNCIA EFETUADA EM " + sdf.format(data);
		buffWrite.append(linha + "\n");

		linha = "******************************************************************************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void tributacaoContaCorrente(String path, Usuario usuario, Conta conta) throws IOException {
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "tributacaoContaCorrente_".toUpperCase() + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		double tributoDeposito = (conta.getQuantidadeDeposito() * Tributos.valorDeposito);
		double tributoSaque = (conta.getQuantidadeSaque() * Tributos.valorSaque);
		double tributoTransferencia = (conta.getQuantidadeTranferencia() * Tributos.valorTransferencia);
		double total = tributoDeposito + tributoSaque + tributoTransferencia;
		DecimalFormat df = new DecimalFormat("#.##");

		String linha = "******************************************************************************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "TRIBUTAÇÃO TOTAL DA CONTA CORRENTE: ";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "QUANTIDADE DE SAQUES: " + conta.getQuantidadeSaque();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "QUANTIDADE DE DEPOSITOS: " + conta.getQuantidadeDeposito();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "QUANTIDADE DE TRANSFERENCIAS: " + conta.getQuantidadeTranferencia();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "VALOR POR TRANSFERENCIA: " + Tributos.valorTransferencia;
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "VALOR POR DEPOSITO: " + Tributos.valorDeposito;
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "VALOR POR SAQUE: " + Tributos.valorSaque;
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "TOTAL: " + df.format(total);
		buffWrite.append(linha + "\n");
		linha = "******************************************************************************************************";
		buffWrite.append(linha + "\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		linha = Utils.centraliza() + "TRANSFERÊNCIA EFETUADA EM " + sdf.format(data);
		buffWrite.append(linha + "\n");
		linha = "******************************************************************************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void relatorioRendimento(String path, Usuario usuario, Conta conta, double valor, int dias)
			throws IOException {
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "RelatorioPoupanca".toUpperCase() + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		DecimalFormat df = new DecimalFormat("#.##");

		String linha = "*************************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "OLÁ " + usuario.getNome().toUpperCase() + " este é o seu relatório de rendimento: ".toUpperCase();
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "SIMULADOR POUPANÇA: ";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "VALOR SIMULADO: " + (valor);
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "TEMPO EM DIAS: " + (dias);
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "RENDIMENTO: "
				+ df.format((Math.pow((1 + ((ContaPoupanca) conta).getRendimento()), dias) * valor));
		buffWrite.append(linha + "\n");
		linha = "*************************************************";
		buffWrite.append(linha + "\n");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		linha = Utils.centraliza() + "TRANSFERÊNCIA EFETUADA EM " + sdf.format(data);
		buffWrite.append(linha + "\n");
		linha = "*************************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void relatorioGerente(String path, ArrayList<Conta> aux, int total, Usuario usuario)
			throws IOException {
		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "relatorioGerente".toUpperCase() + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		String linha = "****************************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "RELATÓRIO DO GERENTE: \n";
		buffWrite.append(linha + "\n");
		for (Conta conta : aux) {
			linha = Utils.centraliza() + "CPF: " + conta.getCpfTitular() + " NÚMERO DA CONTA: " + conta.getNumero();
			buffWrite.append(linha + "\n");
		}
		linha = Utils.centraliza() + "\nO GERENTE " + usuario.getNome().toUpperCase() + " GERÊNCIA UM TOTAL DE " + total
				+ " CONTAS.";
		buffWrite.append(linha + "\n");

		linha = "****************************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();
	}

	public static void relatorioDiretor(String path, List<Usuario> usuarios, Usuario usuario) throws IOException {

		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "relatorioDiretor_".toUpperCase() + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		String linha = "******************************************************************************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "RELATÓRIO DO GERENTE: \n";
		buffWrite.append(linha + "\n");

		for (Usuario u : usuarios) {
			linha = Utils.centraliza() + "NOME: " + u.getNome();
			buffWrite.append(linha + "\n");
			linha = Utils.centraliza() + "CPF: " + u.getCpf();
			buffWrite.append(linha + "\n");
			linha = Utils.centraliza() + "AGÊNCIA: " + u.getAgencia();
			buffWrite.append(linha + "\n");
		}

		linha = "******************************************************************************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();

	}
	
	public static void relatorioPresidente(String path, double capitalTotal, Usuario usuario) throws IOException {

		SimpleDateFormat formataData = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
		Date data = new Date();

		path += "relatoriopresidente_".toUpperCase() + formataData.format(data) + usuario.getNome() + ".bancoBeta";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		String linha = "*************************************************";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "RELATÓRIO DO CAPITAL ARMAZENADO: \n";
		buffWrite.append(linha + "\n");
		linha = Utils.centraliza() + "CAPITAL ARMAZENADO NO BANCO:"+ capitalTotal + "\n";
		buffWrite.append(linha + "\n");
		linha = "*************************************************";
		buffWrite.append(linha + "\n");
		buffWrite.close();

	}
}
