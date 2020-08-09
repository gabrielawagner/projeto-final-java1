package br.com.serratec.javaFinal.menus;

import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.ContaCorrente;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.contaBancaria.SeguroVida;
import br.com.serratec.javaFinal.contaBancaria.Tributos;
import br.com.serratec.javaFinal.usuarios.Usuario;

public class Menu {
	Scanner input = new Scanner(System.in);
	ContaCorrente cc = new ContaCorrente(4, "123.456.789-10", 2000.0);
	ContaCorrente cc2 = new ContaCorrente(1, "123.456.789-10", 2000.0);
	ContaPoupanca cp = new ContaPoupanca(1, "123.456.789-10", 2000.0);
	// TODO Perguntar ao marcelo como resolver a navegação do menu bug ao gerar relatorio.

	public void cliente(Usuario usuario) {

		System.out.println("Bem vindo escolha: [1]Movimentações ou [2]Relatórios");
		int resposta = input.nextInt();
		switch (resposta) {
		case 1:
			movimentacoesConta(usuario);
			break;
		case 2:
			System.out.println("[1]-Saldo [2]-Tributação da conta corrente [3]-Simulador Poupança [4]-Contratar seguro de vida [5]-Voltar [6]-Finalizar");
			int resposta2 = input.nextInt();
			switch (resposta2) {
			case 1:
				System.out.print("SALDO: ");
				System.out.println(cc.getSaldo());
				break;
			case 2:
				System.out.println("Tributação Total da Conta Corrente: ");
				double tributoDeposito = (cc.getQuantidadeDeposito() * Tributos.depositoTributo);
				double tributoSaque = (cc.getQuantidadeSaque() * Tributos.saqueTributo);
				double tributoTransferencia = (cc.getQuantidadeTranferencia() * Tributos.transferenciaTributo);
				System.out.println(tributoDeposito + tributoSaque + tributoTransferencia);
				break;
			case 3:
				// TODO Metodo de calcular apartir da data inserida
				System.out.println("Simulador de Rendimento da Poupança: ");
				System.out.print("Valor: ");
				double valor = input.nextDouble();
				System.out.print("Quantidade de dias: ");
				int dias = input.nextInt();
				double rendimentoDiario = cp.getRendimento() / 30;
				System.out.println("Seu rendimento foi: " + (valor * (dias * rendimentoDiario) / 100));
				break;
			case 4:
				cliente(usuario);
				break;
			case 5:
				break;
			case 6:
				SeguroVida s = new SeguroVida();
				s.contrataSeguro(usuario, cc);
				break;
			}
		}
	}

	public void principal(Usuario usuario) {
		String tipo = usuario.getCargo();
		switch (tipo) {
		case ("Cliente"):
			cliente(usuario);
			break;
		}
	}

	public void movimentacoesConta(Usuario usuario) {
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
				principal(usuario);
				break;
			case 5:
				sair = true;
				break;
			}
		} while (sair == false);
	}

}
