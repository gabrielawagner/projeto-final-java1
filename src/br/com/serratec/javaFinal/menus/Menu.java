package br.com.serratec.javaFinal.menus;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.contaBancaria.SeguroVida;
import br.com.serratec.javaFinal.contaBancaria.Tributos;
import br.com.serratec.javaFinal.enums.TiposUsuarios;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.LimpaTela;

public class Menu {
	private Usuario usuario;
	private Scanner input = new Scanner(System.in);
	private DecimalFormat df = new DecimalFormat("#.##");
	private Conta conta;

	public Menu() {
	}

	public void principal(List<Usuario> usuarios, ArrayList<Conta> contas) throws IOException {
		if (this.usuario.getTipo().equals(TiposUsuarios.CLIENTE.name())) {
			cliente();
		} else if (this.usuario.getTipo().equals(TiposUsuarios.GERENTE.name())) {
			System.out.println("Qual menu? [1]Cliente [2]Gerente");// TODO resolver recursividade
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente();
			} else if (opcao == 2) {
				gerente(contas);
				System.out.println("Insira qualquer tecla para continuar.");
				input.next();
				LimpaTela.limpaConsole();
				principal(usuarios, contas);
			}
		} else if (this.usuario.getTipo().equals(TiposUsuarios.DIRETOR.name())) {
			System.out.println("Qual menu? [1]Cliente [2]Diretor");
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente();
			} else if (opcao == 2) {// TODO JOGO DO BICHO??? ou vale oq esta escrito?
				for (Usuario usuario : usuarios) {
					// Collections.sort(usuarios); TODO SE O MARCELO APARECER PERGUNTAR
					System.out.println("Nome: " + usuario.getNome());
					System.out.println("CPF: " + usuario.getCpf());
					System.out.println("Agencia: " + usuario.getAgencia());
				}
			}
		} 
	}

	public void cliente() throws IOException {

		System.out.println("Bem vindo escolha: [1]Movimentações ou [2]Relatórios");
		int resposta = input.nextInt();
		LimpaTela.limpaConsole();
		switch (resposta) {
		case 1:
			movimentacoes();
			break;
		case 2:
			relatoriosCliente();
			break;
		default:
			System.out.println("Opção Invalida!");
			cliente();
		}
	}

	public void movimentacoes() throws IOException {
		boolean sair = false;
		do {
			System.out.println("\nDigite: \n[1]Saque [2]Depósito [3]Transferência [4]Voltar [5]Finalizar");
			int resposta2;
			resposta2 = input.nextInt();
			LimpaTela.limpaConsole();
			switch (resposta2) {
			case 1:
				System.out.println("\n--------------------Saque-------------------");
				System.out.println("-----------Qual o valor a ser sacado?-------");
				System.out.println("--------------------------------------------");
				double valorSaque = input.nextDouble();
				LimpaTela.limpaConsole();
				conta.sacar(valorSaque);
				break;
			case 2:
				System.out.println("\n------------------Deposito-------------------");
				System.out.println("---------Qual o valor a ser depositado?------");
				System.out.println("---------------------------------------------");
				double valorDeposito = input.nextDouble();
				LimpaTela.limpaConsole();
				conta.depositar(valorDeposito);
				break;
			case 3:
				System.out.println("\n------------------Transferencia--------------");
				System.out.println("------Digite o valor a ser transferido:------");
				System.out.println("---------------------------------------------");
				double valorTransferencia = input.nextDouble();
				LimpaTela.limpaConsole();
				System.out.println("Numero da conta: ");
				// TODO voltar aqui resolver transfere
				// conta.transfere(cp, valorTransferencia);
				break;
			case 4:
				cliente();
				break;
			case 5:
				System.exit(0);
				sair = true;
				break;
			}
		} while (sair == false);
	}

	public void gerente(ArrayList<Conta> contas) throws IOException {
		LimpaTela.limpaConsole();
		String agencia = this.usuario.getAgencia();
		System.out.println("Gerente " + usuario.getNome() + " suas contas gerenciadas são: ");
		int total = 0;
		for (Conta conta : contas) {
			if (conta.getAgencia().equals(agencia)) {
				total++;
				System.out.println("CPF: " + conta.getCpfTitular() + " - número da conta: " + conta.getNumero());
			}
		}
		System.out.println("Sua contas gerenciadas na agencia " + agencia + " são no total: " + total);
	}

	public void relatorios() throws IOException {
		System.out.println(
				"[1]-Saldo [2]-Tributação da conta corrente [3]-Simulador Poupança [4]-Contratar seguro de vida [5]-Voltar [6]-Finalizar");
		int resposta2 = input.nextInt();
		switch (resposta2) {
		case 1:
			LimpaTela.limpaConsole();
			System.out.print("SALDO: ");
			System.out.println(conta.getSaldo());
			System.out.println();
			relatoriosCliente();
			break;
		case 2:
			System.out.println("Tributação Total da Conta Corrente: ");
			double tributoDeposito = (conta.getQuantidadeDeposito() * Tributos.valorDeposito);
			double tributoSaque = (conta.getQuantidadeSaque() * Tributos.valorSaque);
			double tributoTransferencia = (conta.getQuantidadeTranferencia() * Tributos.valorTransferencia);
			LimpaTela.limpaConsole();
			System.out.println("Valor por transferencia: " + Tributos.valorTransferencia);
			System.out.println("Valor por deposito: " + Tributos.valorDeposito);
			System.out.println("Valor por saque: " + Tributos.valorSaque);
			System.out.println("Total: " + (tributoDeposito + tributoSaque + tributoTransferencia));
			relatoriosCliente();
			break;
		case 3:
			// TODO Metodo de calcular apartir da data inserida
			System.out.println("Simulador de Rendimento da Poupança: ");
			System.out.print("Valor: ");
			double valor = input.nextDouble();
			System.out.print("Quantidade de dias: ");
			int dias = input.nextInt();
			LimpaTela.limpaConsole();// TODO java.lang.ClassCastException: 
			System.out.println("Seu rendimento foi: "
					+ df.format(Math.pow((1 + ((ContaPoupanca) conta).getRendimento()), dias) * valor));
			relatoriosCliente();
			break;
		case 4:
			SeguroVida s = new SeguroVida();
			resposta2 = s.contrataSeguro(this.usuario, this.conta);
			break;
		case 5:
			cliente();
			break;
		case 6:
			break;
		}
	}

	public void relatoriosCliente() throws IOException {
		relatorios();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
