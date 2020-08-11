package br.com.serratec.javaFinal.menus;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
	
	/**
	 * Inicializa o menu principal
	 * 
	 * @param usuario - usuario logado
	 * @param conta - conta bancaria do usuario logado
	 * @param usuarios - lista de usuarios carregada do arquivo txt.
	 * @param contas - lista de contas carregada do arquivo txt.
	 */
	public void principal(Usuario usuario, Conta conta, List<Usuario> usuarios, ArrayList<Conta> contas) throws IOException {
		if (this.usuario.getTipo().equals(TiposUsuarios.CLIENTE.name())) {
			cliente(contas);
		} else if (this.usuario.getTipo().equals(TiposUsuarios.GERENTE.name())) {
			System.out.println("Qual menu? [1]Cliente [2]Gerente");// TODO resolver recursividade
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente(contas);
			} else if (opcao == 2) {
				gerente(contas);
				System.out.println("Insira qualquer tecla para continuar.");
				input.next();
				LimpaTela.limpaConsole();
				principal(usuario, conta, usuarios, contas);
			}
		} else if (this.usuario.getTipo().equals(TiposUsuarios.DIRETOR.name())) {
			System.out.println("Qual menu? [1]Cliente [2]Diretor");
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente(contas);
			} else if (opcao == 2) {// TODO JOGO DO BICHO??? ou vale oq esta escrito?
				for (Usuario u : usuarios) {
					// Collections.sort(usuarios); TODO SE O MARCELO APARECER PERGUNTAR
					System.out.println("Nome: " + u.getNome());
					System.out.println("CPF: " + u.getCpf());
					System.out.println("Agencia: " + u.getAgencia());
				}
			}
		} 
	}

	public void cliente(List<Conta> contas) throws IOException {

		System.out.println("Bem vindo escolha: [1]Movimenta��es ou [2]Relat�rios");
		int resposta = input.nextInt();
		LimpaTela.limpaConsole();
		switch (resposta) {
		case 1:
			movimentacoes(contas);
			break;
		case 2:
			relatorios(contas);
			break;
		default:
			System.out.println("Op��o Invalida!");
			cliente(contas);
		}
	}

	public void movimentacoes(List<Conta> contas) throws IOException {
		boolean sair = false;
		do {
			System.out.println("\nDigite: \n[1]Saque [2]Dep�sito [3]Transfer�ncia [4]Voltar [5]Finalizar");
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
				input.nextLine();
				String numeroContaDestino = input.nextLine();
				Conta destino = buscaContaDestino(numeroContaDestino, contas);
				System.out.println(destino.getSaldo());
				conta.transfere(destino, valorTransferencia);
				System.out.println(destino.getSaldo());
				break;
			case 4:
				cliente(contas);
				break;
			case 5:
				System.exit(0);
				sair = true;
				break;
			}
		} while (sair == false);
	}

	private Conta buscaContaDestino(String numeroContaDestino, List<Conta> contas) {
		for (Conta conta : contas) {
			if(numeroContaDestino.equals(conta.getNumero())) {
				return conta;
			}
		}
		return null;
	}

	public void gerente(ArrayList<Conta> contas) throws IOException {
		LimpaTela.limpaConsole();
		String agencia = this.usuario.getAgencia();
		System.out.println("Gerente " + usuario.getNome() + " suas contas gerenciadas s�o: ");
		int total = 0;
		for (Conta conta : contas) {
			if (conta.getAgencia().equals(agencia)) {
				total++;
				System.out.println("CPF: " + conta.getCpfTitular() + " - n�mero da conta: " + conta.getNumero());
			}
		}
		System.out.println("Sua contas gerenciadas na agencia " + agencia + " s�o no total: " + total);
	}

	public void relatorios(List<Conta> contas) throws IOException {
		System.out.println(
				"[1]-Saldo [2]-Tributa��o da conta corrente [3]-Simulador Poupan�a [4]-Contratar seguro de vida [5]-Voltar [6]-Finalizar");
		int resposta2 = input.nextInt();
		switch (resposta2) {
		case 1:
			LimpaTela.limpaConsole();
			System.out.print("SALDO: ");
			System.out.println(conta.getSaldo());
			System.out.println();
			relatorios(contas);
			break;
		case 2:
			System.out.println("Tributa��o Total da Conta Corrente: ");
			double tributoDeposito = (conta.getQuantidadeDeposito() * Tributos.valorDeposito);
			double tributoSaque = (conta.getQuantidadeSaque() * Tributos.valorSaque);
			double tributoTransferencia = (conta.getQuantidadeTranferencia() * Tributos.valorTransferencia);
			LimpaTela.limpaConsole();
			System.out.println("Valor por transferencia: " + Tributos.valorTransferencia);
			System.out.println("Valor por deposito: " + Tributos.valorDeposito);
			System.out.println("Valor por saque: " + Tributos.valorSaque);
			System.out.println("Total: " + (tributoDeposito + tributoSaque + tributoTransferencia));
			relatorios(contas);
			break;
		case 3:
			// TODO Metodo de calcular apartir da data inserida
			System.out.println("Simulador de Rendimento da Poupan�a: ");
			System.out.print("Valor: ");
			double valor = input.nextDouble();
			System.out.print("Quantidade de dias: ");
			int dias = input.nextInt();
			LimpaTela.limpaConsole();// TODO java.lang.ClassCastException: 
			System.out.println("Seu rendimento foi: "
					+ df.format(Math.pow((1 + ((ContaPoupanca) conta).getRendimento()), dias) * valor));
			relatorios(contas);
			break;
		case 4:
			SeguroVida s = new SeguroVida();
			resposta2 = s.contrataSeguro(this.usuario, this.conta);
			break;
		case 5:
			cliente(contas);
			break;
		case 6:
			break;
		}
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
