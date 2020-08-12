package br.com.serratec.javaFinal.menus;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
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
			System.out.println( "Qual menu? [1]Cliente [2]Gerente");// TODO resolver recursividade
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {
				gerente(usuario, contas);
				continuar(usuario, conta, usuarios, contas);
			}
		} else if (usuario.getTipo().equals(EnumUsuarios.DIRETOR.name())) {
			System.out.println("Qual menu? [1]Cliente [2]Diretor");
			Collections.sort(usuarios);//// TODO SE O MARCELO APARECER PERGUNTAR COMO RESOLVER O SORT
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {
				for (Usuario u : usuarios) {
					System.out.println("Nome: " + u.getNome());
					System.out.println("CPF: " + u.getCpf());
					System.out.println("Agencia: " + u.getAgencia());
					System.out.println();
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
				for (Conta c : contas) {
					capitalTotal += c.getSaldo();
				}
				System.out.println("O valor total do capital armazenado no banco é: " + capitalTotal);
				continuar(usuario, conta, usuarios, contas);
			}
		}
	}

	private void continuar(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
		System.out.println("\n\nInsira qualquer tecla para continuar.");
		input.next();
		LimpaTela.limpaConsole();
		principal(usuario, conta, usuarios, contas);
	}

	public void cliente(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) throws IOException {

		if (usuario.getTipo().equals(EnumUsuarios.CLIENTE.name())) {

			System.out.println("[1]Movimentações ou [2]Relatórios");
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
			System.out.println("Digite: \n[1]Saque [2]Depósito [3]Transferência [4]Voltar [5]Finalizar");
			int resposta2;
			resposta2 = input.nextInt();
			LimpaTela.limpaConsole();
			switch (resposta2) {
			case 1:
				System.out.println("\n--------------------Saque-------------------");
				System.out.println("-----------Qual o valor a ser sacado?-------");
				System.out.println("--------------------------------------------");
				double valorSaque = input.nextDouble();
				ArquivoUtils.saque("./", usuario, conta, valorSaque);
				LimpaTela.limpaConsole();
				conta.sacar(valorSaque);
				continuar(usuario, conta, usuarios, contas);
				LimpaTela.limpaConsole();
				break;
			case 2:
				System.out.println("\n------------------Deposito-------------------");
				System.out.println("---------Qual o valor a ser depositado?------");
				System.out.println("---------------------------------------------");
				double valorDeposito = input.nextDouble();
				ArquivoUtils.deposita("./", usuario, conta, valorDeposito);
				LimpaTela.limpaConsole();
				conta.depositar(valorDeposito);
				continuar(usuario, conta, usuarios, contas);
				LimpaTela.limpaConsole();
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
				Conta destino = buscaContaDestino(numeroContaDestino, contas);// TODO Implementar contaNaoEXisteException
				ArquivoUtils.transferencia("./", usuario, conta, destino, valorTransferencia);
				conta.transfere(destino, valorTransferencia);
				continuar(usuario, conta, usuarios, contas);
				LimpaTela.limpaConsole();
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
			ArquivoUtils.saldo("./", usuario, conta);
			LimpaTela.limpaConsole();
			System.out.format("O Saldo Da Sua Conta é: %.2f", conta.getSaldo());
			continuar(usuario, conta, usuarios, contas);
			LimpaTela.limpaConsole();
			relatorios(usuario, conta, usuarios, contas);
			break;
		case 2:
			System.out.println("Tributação Total da Conta Corrente: ");
			double tributoDeposito = (conta.getQuantidadeDeposito() * Tributos.valorDeposito);
			double tributoSaque = (conta.getQuantidadeSaque() * Tributos.valorSaque);
			double tributoTransferencia = (conta.getQuantidadeTranferencia() * Tributos.valorTransferencia);
			LimpaTela.limpaConsole();
			System.out.println("Quantidade de Saques: " + conta.getQuantidadeSaque());
			System.out.println("Quantidade de Depositos: " + conta.getQuantidadeDeposito());
			System.out.println("Quantidade de Transferencias: " + conta.getQuantidadeTranferencia());
			System.out.println("Valor por transferencia: " + Tributos.valorTransferencia);
			System.out.println("Valor por deposito: " + Tributos.valorDeposito);
			System.out.println("Valor por saque: " + Tributos.valorSaque);
			System.out.format("Total: %.2f",(tributoDeposito + tributoSaque + tributoTransferencia));
			ArquivoUtils.tributacaoContaCorrente("./", usuario, conta);
			continuar(usuario, conta, usuarios, contas);
			LimpaTela.limpaConsole();
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
			System.out.format("Seu rendimento foi: %.2f", (Math.pow((1 + ((ContaPoupanca) conta).getRendimento()), dias) * valor));
			continuar(usuario, conta, usuarios, contas);
			LimpaTela.limpaConsole();
			relatorios(usuario, conta, usuarios, contas);
			break;
		case 4:
			//TODO RESOLVER DEPOIS SEGURO DE VIDA
			SeguroVida s = new SeguroVida();
			resposta2 = s.contrataSeguro(usuario, conta);
			break;
		case 5:
			cliente(usuario, conta, usuarios, contas);
			break;
		case 6:
			break;
		}
	}
}
