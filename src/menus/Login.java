package menus;

import java.util.Scanner;

import src.contaBancaria.ContaCorrente;

public class Login {
	Scanner Input = new Scanner(System.in);
	ContaCorrente i = new ContaCorrente(4, "123.456.789-10", 2400.0);

	/*
	 * String cpf, senha; System.out.println("CPF: "); cpf = Input.nextLine();
	 * System.out.println("Senha: "); senha = Input.nextLine();
	 */
	public void LogCliente() {
		System.out.println("Bem vindo ao banco X, por favor, Insira [1]Movimentações" + " ou [2]Relatórios");
		int resposta;
		resposta = Input.nextInt();
		switch (resposta) {
		case 1:
			System.out.println("Digite [1]Saque \n[2]Depósito \n[3]Transferência");
			int resposta2;
			resposta2 = Input.nextInt();
			switch (resposta2) {
			case 1:
				System.out.println("Qual o valor do saque?");
				double valorSaque;
				valorSaque = Input.nextDouble();
				i.sacar(valorSaque);

				break;
			case 2:

				break;
			case 3:

				break;

			default:
				break;
			}
			break;
		case 2:

			break;
		default:
			System.out.println("Opção invalida");
			break;
		}

	}

}
