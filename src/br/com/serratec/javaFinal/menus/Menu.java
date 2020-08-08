package br.com.serratec.javaFinal.menus;

import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;

public class Menu {
	Scanner input = new Scanner(System.in);
	ContaCorrente cc = new ContaCorrente(4, "123.456.789-10", 2000.0);
	ContaCorrente cc2 = new ContaCorrente(1, "123.456.789-10", 2000.0);

	/*
	 * String cpf, senha; System.out.println("CPF: "); cpf = Input.nextLine();
	 * System.out.println("Senha: "); senha = Input.nextLine();
	 */
	public void cliente() {
		System.out.println("Bem vindo ao banco X, por favor, Insira [1]Movimentações ou [2]Relatórios");
		int resposta = input.nextInt();
		switch (resposta) {
		case 1:
			boolean sair = false;
			do {
				System.out.println("Digite \n[1]Saque [2]Depósito [3]Transferência [4]Finalizar");
				int resposta2;
				resposta2 = input.nextInt();
				switch (resposta2) {
				case 1:
					System.out.println("Qual o valor do saque?");
					double valorSaque = input.nextDouble();
					cc.sacar(valorSaque);
					break;
				case 2:
					System.out.println("Qual o valor do deposito?");
					double valorDeposito = input.nextDouble();
					cc.depositar(valorDeposito);
					break;
				case 3:
					System.out.println("Qual o valor do deposito?");
					double valorTransferencia = input.nextDouble();
					cc.transfere(cc2, valorTransferencia);
					break;
				case 4:
					sair = true;
					break;
				default:
					break;
				}
			} while (sair == false);
			break;
		case 2:

			break;
		default:
			System.out.println("Opção invalida");
			break;
		}
	}

}
