package br.com.serratec.javaFinal.menus;

import java.io.IOException;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.contaBancaria.SeguroVida;
import br.com.serratec.javaFinal.contaBancaria.Tributos;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.LimpaTela;

public class Menu {
	Usuario usuario;
	Conta conta;
	Scanner input = new Scanner(System.in);
	ContaPoupanca cp = new ContaPoupanca(0, "15050", 2500);
	
	public Menu(Usuario usuario, Conta conta) {
		this.usuario = usuario;
		this.conta = conta;
	}

	public void cliente(Usuario usuario) throws IOException {

		System.out.println("Bem vindo escolha: [1]Movimentações ou [2]Relatórios");
		int resposta = input.nextInt();
		LimpaTela.clearConsole();
		switch (resposta) {
		case 1:
			movimentacoes(usuario);
			break;
		case 2:
			relatorios(usuario);
			break;
		default:
			System.out.println("Opção Invalida!");
			cliente(usuario);
		}
	}

	public void principal(Usuario usuario) throws IOException {
		String tipo = usuario.getTipo();
		switch (tipo) {
		case ("cliente"):
			cliente(usuario);
			break;
		}
	}

	public void relatorios(Usuario usuario) throws IOException {
		System.out.println(
				"[1]-Saldo [2]-Tributação da conta corrente [3]-Simulador Poupança [4]-Contratar seguro de vida [5]-Voltar [6]-Finalizar");
		int resposta2 = input.nextInt();
		switch (resposta2) {
		case 1:
			LimpaTela.clearConsole();
			System.out.print("SALDO: ");
			System.out.println(conta.getSaldo());
			System.out.println();
			relatorios(usuario);
			break;
		case 2:
			System.out.println("Tributação Total da Conta Corrente: ");
			double tributoDeposito = (conta.getQuantidadeDeposito() * Tributos.depositoTributo);
			double tributoSaque = (conta.getQuantidadeSaque() * Tributos.saqueTributo);
			double tributoTransferencia = (conta.getQuantidadeTranferencia() * Tributos.transferenciaTributo);
			LimpaTela.clearConsole();
			System.out.println(tributoDeposito + tributoSaque + tributoTransferencia);
			relatorios(usuario);
			break;
		case 3:
			// TODO Metodo de calcular apartir da data inserida
			System.out.println("Simulador de Rendimento da Poupança: ");
			System.out.print("Valor: ");
			double valor = input.nextDouble();
			System.out.print("Quantidade de dias: ");
			int dias = input.nextInt();
			double rendimentoDiario = cp.getRendimento() / 30;
			LimpaTela.clearConsole();
			System.out.println("Seu rendimento foi: " + (valor * (dias * rendimentoDiario) / 100));
			relatorios(usuario);
			break;
		case 4:
			SeguroVida s = new SeguroVida();
			resposta2 = s.contrataSeguro(usuario, conta);
			break;
		case 5:
			cliente(usuario);
			break;
		case 6:
			break;
		}
	}

	public void movimentacoes(Usuario usuario) throws IOException {
		boolean sair = false;
		do {
			System.out.println("\nDigite: \n[1]Saque [2]Depósito [3]Transferência [4]Voltar [5]Finalizar");
			int resposta2;
			resposta2 = input.nextInt();
			LimpaTela.clearConsole();
			switch (resposta2) {
			case 1:
				System.out.println("\n--------------------Saque-------------------");
				System.out.println("-----------Qual o valor a ser sacado?-------");
				System.out.println("--------------------------------------------");
				double valorSaque = input.nextDouble();
				LimpaTela.clearConsole();
				conta.sacar(valorSaque);
				break;
			case 2:
				System.out.println("\n------------------Deposito-------------------");
				System.out.println("---------Qual o valor a ser depositado?------");
				System.out.println("---------------------------------------------");
				double valorDeposito = input.nextDouble();
				LimpaTela.clearConsole();
				conta.depositar(valorDeposito);
				break;
			case 3:
				System.out.println("\n------------------Transferencia--------------");
				System.out.println("------Digite o valor a ser transferido:------");
				System.out.println("---------------------------------------------");
				double valorTransferencia = input.nextDouble();
				LimpaTela.clearConsole();
				conta.transfere(cp, valorTransferencia);
				break;
			case 4:
				principal(usuario);
				break;
			case 5:
				sair = true;
				break;
			}
		} while (sair == false);
	}

}
