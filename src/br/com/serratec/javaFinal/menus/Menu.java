package br.com.serratec.javaFinal.menus;

import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;
import br.com.serratec.javaFinal.contaBancaria.Tributos;
import br.com.serratec.javaFinal.pessoal.Funcionario;

public class Menu {
	Scanner input = new Scanner(System.in);
	ContaCorrente cc = new ContaCorrente(4, "123.456.789-10", 2000.0);
	ContaCorrente cc2 = new ContaCorrente(1, "123.456.789-10", 2000.0);
	//TODO Perguntar ao marcelo como resolver a navegação do menu bug ao gerar relatorio.
	
	public void cliente(Funcionario f) {
		System.out.println("Bem vindo escolha: [1]Movimentações ou [2]Relatórios");
		int resposta = input.nextInt();
		switch (resposta) {
		case 1:
			
			movimentacoesConta(f);
			break;
		case 2:
			System.out.println("Saldo [1] ou [2] Tributação da conta corrente");
			int resposta2 = input.nextInt();
			switch (resposta2) {
			case 1:
				System.out.print("SALDO: ");
				System.out.println(cc.getSaldo());
				break;
			case 2:
				System.out.println("Tributação total da conta: ");
				double tributoDeposito = (cc.getQuantidadeDeposito() * Tributos.depositoTributo);
				double tributoSaque = (cc.getQuantidadeSaque() * Tributos.saqueTributo);
				double tributoTransferencia = (cc.getQuantidadeTranferencia() * Tributos.transferenciaTributo);
				System.out.println(tributoDeposito + tributoSaque + tributoTransferencia);
			}
		}
	}
	
	public void principal(Funcionario f) {
		String tipo = f.getCargo();
		switch (tipo) {
		case ("Cliente"):
			cliente(f);
			break;
		}
	}

	public void movimentacoesConta(Funcionario f) {
		boolean sair = false;
		do {
			System.out.println("\nDigite: \n[1]Saque [2]Depósito [3]Transferência [4]Voltar [5]Finalizar");
			int resposta2;
			resposta2 = input.nextInt();
			switch (resposta2) {
			case 1:
				System.out.println("\n--------------------Saque-------------------");
				System.out.println("-----------Qual o valor a ser sacado?-------");
				System.out.println("--------------------------------------------");
				double valorSaque = input.nextDouble();
				cc.sacar(valorSaque);
				resposta = 0;
				break;
			case 2:
				System.out.println("\n------------------Deposito-------------------");
				System.out.println("---------Qual o valor a ser depositado?------");
				System.out.println("---------------------------------------------");
				double valorDeposito = input.nextDouble();
				cc.depositar(valorDeposito);
				break;
			case 3:
				System.out.println("\n------------------Transferencia--------------");
				System.out.println("------Digite o valor a ser transferido:------");
				System.out.println("---------------------------------------------");
				double valorTransferencia = input.nextDouble();
				cc.transfere(cc2, valorTransferencia);
				break;
			case 4:
				principal(f); 
				break;
			case 5:
				sair = true;
				break;
			default:
				System.out.println("Operação Invalida!");
				break;
			}
		} while (sair == false);
	}

}
