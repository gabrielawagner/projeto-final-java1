package menus;

import java.util.Scanner;

import src.contaBancaria.ContaCorrente;

public class Login {
	Scanner input = new Scanner(System.in);
	ContaCorrente cc = new ContaCorrente(4, "123.456.789-10", 2000.0);

	/*
	 * String cpf, senha; System.out.println("CPF: "); cpf = Input.nextLine();
	 * System.out.println("Senha: "); senha = Input.nextLine();
	 */
	public void LogCliente() {
		System.out.println("Bem vindo ao banco! \n Selecione a opera��o:\n [1]Movimenta��es" + " ou [2]Relat�rios");
		int resposta = input.nextInt();
		switch (resposta) {
		case 1:
			System.out.println("Selecione:\n [1]Saque [2]Dep�sito [3]Transfer�ncia");
			int resposta2;
			resposta2 = input.nextInt();
			switch (resposta2) {
			case 1:
				System.out.println("--------------------Saque-------------------");
				System.out.println("Qual o valor a ser sacado?");
				System.out.println("--------------------------------------------");
				double valorSaque = input.nextDouble();
				cc.sacar(valorSaque);
				break;
			case 2:
				System.out.println("------------------Deposito-------------------");
				System.out.println("Qual o valor a ser depositado?");
				System.out.println("---------------------------------------------");
				//double valorDeposito = input.nextDouble();
				//cc.despositar(valorDeposito);
				break;
				
			case 3:
				System.out.println("------------------Transferencia--------------");
				System.out.println("Digite o valor a ser transferido:");
				System.out.println("---------------------------------------------");
				//double valorTransferencia = input.nextDouble();
				//cc.transfere(valorTransferencia);
				break;
			default:
				break;
			}
			break;
		case 2:
			
			break;
		default:
			System.out.println("Op��o invalida");
			break;
		}
		
	}
	
}
				
				
				

			
				
				
