package br.com.serratec.javaFinal.menus;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.contaBancaria.SeguroVida;
import br.com.serratec.javaFinal.contaBancaria.Tributos;
import br.com.serratec.javaFinal.enums.EnumUsuarios;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.ArquivoUtils;
import br.com.serratec.javaFinal.utils.LimpaTela;

public class Menu {
	private Scanner input = new Scanner(System.in);
	private DecimalFormat df = new DecimalFormat("#.##");

	public Menu() {
	}

	/**
	 * Inicializa o menu principal
	 * 
	 * @param usuario  - usuario logado
	 * @param conta    - conta bancaria do usuario logado
	 * @param usuarios - lista de usuarios carregada do arquivo txt.
	 * @param contas   - lista de contas carregada do arquivo txt.
	 */
	public void principal(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) throws IOException {
		if (usuario.getTipo().equals(EnumUsuarios.CLIENTE.name())) {
			cliente(usuario, conta, usuarios, contas);
		} else if (usuario.getTipo().equals(EnumUsuarios.GERENTE.name())) {
			System.out.println("INFORME O TIPO DE ACESSO \n [1]Cliente [2]Gerente");// TODO resolver recursividade
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {
				gerente(usuario, contas);
				continuar(usuario, conta, usuarios, contas);
			}
		} else if (usuario.getTipo().equals(EnumUsuarios.DIRETOR.name())) {
			System.out.println("INFORME O TIPO DE ACESSO \n [1]Cliente [2]Diretor");
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {// TODO JOGO DO BICHO??? ou vale oq esta escrito?
				for (Usuario u : usuarios) {
					// Collections.sort(usuarios);//// TODO SE O MARCELO APARECER PERGUNTAR
					System.out.println("[1] RELATÓRIO DOS CLIENTES DO BANCO");
					opcao = input.nextInt();
					if (opcao == 1)
					System.out.println("NOME" + u.getNome());
					System.out.println("CPF: " + u.getCpf());
					System.out.println("AGÊNCIA: " + u.getAgencia());
				}
				continuar(usuario, conta, usuarios, contas);
			}
		} else if (usuario.getTipo().equals(EnumUsuarios.PRESIDENTE.name())) {
			System.out.println("Qual menu? [1]Cliente [2]Presidente");
			int opcao = input.nextInt();
			double capitalTotal = 0;
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {
				System.out.println("[1] RELATÓRIO DO CAPITAL ARMAZENDADO NO BANCO");
				opcao = input.nextInt();
				if (opcao == 1)
				for (Conta c : contas) {
					capitalTotal += c.getSaldo();
				}
				System.out.println("CAPITAL ARMAZENADO NO BANCO: " + capitalTotal);
				continuar(usuario, conta, usuarios, contas);
			}
		}
	}

	private void continuar(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
		System.out.println("Insira qualquer tecla para continuar.");
		input.next();
		LimpaTela.limpaConsole();
		principal(usuario, conta, usuarios, contas);
	}

	public void cliente(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) throws IOException {

		if (usuario.getTipo().equals(EnumUsuarios.CLIENTE.name())) {

			System.out.println("Bem vindo escolha: [1]Movimentações ou [2]Relatórios");
			int resposta = input.nextInt();
			LimpaTela.limpaConsole();
			switch (resposta) {
			case 1:
				movimentacoes(usuario, conta, usuarios, contas);
				break;
			case 2:
				relatorios(usuario, conta, usuarios, contas);
				break;
			default:
				System.out.println("Opção Invalida!");
				cliente(usuario, conta, usuarios, contas);
			}
		} else {
			System.out.println("Bem vindo escolha: [1]Movimentações [2]Relatórios [3]Voltar");
			int resposta = input.nextInt();
			LimpaTela.limpaConsole();
			switch (resposta) {
			case 1:
				movimentacoes(usuario, conta, usuarios, contas);
				break;
			case 2:
				relatorios(usuario, conta, usuarios, contas);
				break;
			case 3:
				principal(usuario, conta, usuarios, contas);
				break;
			default:
				System.out.println("Opção Invalida!");
				cliente(usuario, conta, usuarios, contas);
			}
		}
	}

	public void movimentacoes(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
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
				input.nextLine();
				String numeroContaDestino = input.nextLine();
				Conta destino = buscaContaDestino(numeroContaDestino, contas);// TODO implementar
																				// contaNaoEXisteException
				conta.transfere(destino, valorTransferencia);
				break;
			case 4:
				cliente(usuario, conta, usuarios, contas);
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
			if (numeroContaDestino.equals(conta.getNumero())) {
				return conta;
			}
		}
		return null;
	}

	public void gerente(Usuario usuario, List<Conta> contas) throws IOException {
		LimpaTela.limpaConsole();
		String agencia = usuario.getAgencia();
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

	public void relatorios(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
		System.out.println(
				"[1]-Saldo [2]-Tributação da conta corrente [3]-Simulador Poupança [4]-Contratar seguro de vida [5]-Voltar [6]-Finalizar");
		int resposta2 = input.nextInt();
		switch (resposta2) {
		case 1:
			LimpaTela.limpaConsole();
			System.out.print("SALDO: ");
			System.out.println(conta.getSaldo());
			System.out.println();
			relatorios(usuario, conta, usuarios, contas);
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
			System.out.println("Total: " + df.format((tributoDeposito + tributoSaque + tributoTransferencia)));
			relatorios(usuario, conta, usuarios, contas);
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
			relatorios(usuario, conta, usuarios, contas);
			break;
		case 4:
			SeguroVida s = new SeguroVida();
			resposta2 = s.contrataSeguro(usuario, conta);
			break;
		case 5:
			cliente(usuario, conta, null, contas);
			break;
		case 6:
			break;
		}
	}
}
