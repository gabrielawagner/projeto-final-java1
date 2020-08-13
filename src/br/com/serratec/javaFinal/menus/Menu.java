package br.com.serratec.javaFinal.menus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.serratec.javaFinal.contaBancaria.Conta;
import br.com.serratec.javaFinal.contaBancaria.ContaPoupanca;
import br.com.serratec.javaFinal.contaBancaria.SeguroVida;
import br.com.serratec.javaFinal.contaBancaria.Tributos;
import br.com.serratec.javaFinal.enums.EnumUsuarios;
import br.com.serratec.javaFinal.exceptions.DepositoNegativoException;
import br.com.serratec.javaFinal.usuarios.Usuario;
import br.com.serratec.javaFinal.utils.ArquivoUtils;
import br.com.serratec.javaFinal.utils.LimpaTela;
import br.com.serratec.javaFinal.utils.Utils;

public class Menu {
	private Scanner input = new Scanner(System.in);

	public Menu() {
	}

	/**
	 * Inicializa o menu principal
	 * 
	 * @param usuario  - usuario logado
	 * @param conta    - conta bancaria do usuario logado
	 * @param usuarios - lista de usuarios carregada do arquivo txt.
	 * @param contas   - lista de contas carregada do arquivo txt.
	 **/
	public void principal(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) throws IOException {

		if (usuario.getTipo().equals(EnumUsuarios.CLIENTE.name())) {
			cliente(usuario, conta, usuarios, contas);
		} else if (usuario.getTipo().equals(EnumUsuarios.GERENTE.name())) {
			gerente(usuario, conta, usuarios, contas);
		} else if (usuario.getTipo().equals(EnumUsuarios.DIRETOR.name())) {
			diretor(usuario, conta, usuarios, contas);
		} else if (usuario.getTipo().equals(EnumUsuarios.PRESIDENTE.name())) {
			presidente(usuario, conta, usuarios, contas);
		}
	}

	public void cliente(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) throws IOException {
		int resposta = 0;
		try {
			if (usuario.getTipo().equals(EnumUsuarios.CLIENTE.name())) {
				System.out.println("[1]MOVIMENTAÇÕES [2]RELATÓRIOS");
				resposta = input.nextInt();
				LimpaTela.limpaConsole();
				switch (resposta) {
				case 1:
					movimentacoes(usuario, conta, usuarios, contas);
					break;
				case 2:
					relatorios(usuario, conta, usuarios, contas);
					break;
				default:
					System.out.println("OPÇÃO INVÁLIDA!");
					cliente(usuario, conta, usuarios, contas);
				}
			} else {
				System.out.println("INFORME A OPÇÃO DESEJADA \n\n[1]MOVIMENTAÇÕES [2]RELATÓRIOS [3]VOLTAR");
				resposta = input.nextInt();
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
					System.out.println("Opção Invalida!".toUpperCase());
					cliente(usuario, conta, usuarios, contas);
				}
			}
		} catch (InputMismatchException e) {
			LimpaTela.limpaConsole();
			System.out.println("esta não é uma opção válida.".toUpperCase());
			input.nextLine();
		} finally {
			cliente(usuario, conta, usuarios, contas);
		}
	}

	private void gerente(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) throws IOException {
		System.out.println("INFORME O TIPO DE ACESSO \n\n[1]CLIENTE [2]GERENTE");
		int opcao = input.nextInt();
		try {
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {
				System.out.println("[1]RELATÓRIO DAS CONTAS PERTENCENTES A SUA AGÊNCIA");
				opcao = input.nextInt();
				if (opcao == 1) {
					LimpaTela.limpaConsole();
					String agencia = usuario.getAgencia();
					System.out.println(usuario.getNome().toUpperCase() + ", SUAS CONTAS GERENCIADAS SÃO: \n");
					int total = 0;
					ArrayList<Conta> aux = new ArrayList<>();
					for (Conta c : contas) {
						if (c.getAgencia().equals(agencia)) {
							total++;
							System.out.println(
									"CPF: " + c.getCpfTitular() + " - NÚMERO DA CONTA: " + c.getNumero());
							aux.add(c);
						}
					}
					ArquivoUtils.relatorioGerente("./relatorioGerente/", aux, total, usuario);
					System.out.println("\nO GERENTE " + usuario.getNome().toUpperCase() + " GERENCIA UM TOTAL DE "
							+ total + " CONTAS.");
				}
				continuar(usuario, conta, usuarios, contas);
			}
		} catch (InputMismatchException e) {
			LimpaTela.limpaConsole();
			System.out.println("esta não é uma opção válida.".toUpperCase());
			input.nextLine();
		} finally {
			cliente(usuario, conta, usuarios, contas);
		}
	}

	private void diretor(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) throws IOException {
		try {
			System.out.println("INFORME O TIPO DE ACESSO \n\n[1]CLIENTE [2]DIRETOR");
			Collections.sort(usuarios);
			int opcao = input.nextInt();
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {
				System.out.println("[1]RELATÓRIO DOS CLIENTES DO BANCO");
				opcao = input.nextInt();
				if (opcao == 1)
					for (Usuario u : usuarios) {
						System.out.println("NOME: " + u.getNome());
						System.out.println("CPF: " + u.getCpf());
						System.out.println("AGÊNCIA: " + u.getAgencia());
						System.out.println();
					}
				ArquivoUtils.relatorioDiretor("./relatorioDiretor/", usuarios, usuario);
				continuar(usuario, conta, usuarios, contas);
			}
		} catch (InputMismatchException e) {
			LimpaTela.limpaConsole();
			System.out.println("esta não é uma opção válida.".toUpperCase());
			input.nextLine();
		} finally {
			cliente(usuario, conta, usuarios, contas);
		}
	}

	private void presidente(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
		try {
			System.out.println("INFORME O TIPO DE ACESSO \n\n[1]CLIENTE [2]PRESIDENTE");
			int opcao = input.nextInt();
			double capitalTotal = 0;
			if (opcao == 1) {
				cliente(usuario, conta, usuarios, contas);
			} else if (opcao == 2) {
				System.out.println("[1]RELATÓRIO DO CAPITAL ARMAZENADO NO BANCO");
				opcao = input.nextInt();
				if (opcao == 1)
					for (Conta c : contas) {
						capitalTotal += c.getSaldo();
					}
				ArquivoUtils.relatorioPresidente("./relatorioPresidente/", capitalTotal, usuario);
				System.out.println("CAPITAL ARMAZENADO NO BANCO: " + capitalTotal);
				continuar(usuario, conta, usuarios, contas);
			}
		} catch (InputMismatchException e) {
			LimpaTela.limpaConsole();
			System.out.println("esta não é uma opção válida.".toUpperCase());
			input.nextLine();
		} finally {
			cliente(usuario, conta, usuarios, contas);
		}
	}

	private void continuar(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
		System.out.println("\n\nINSIRA QUALQUER TECLA PARA CONTINUAR.");
		input.next();
		LimpaTela.limpaConsole();
		principal(usuario, conta, usuarios, contas);
	}

	public void movimentacoes(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
		boolean sair = false;
		do {
			System.out.println(
					"INFORME A OPÇÃO DESEJADA \n[1]SAQUE \n[2]DEPÓSITO \n[3]TRANSFERÊNCIA \n[4]VOLTAR \n[5]FINALIZAR");
			int resposta2;
			resposta2 = input.nextInt();
			LimpaTela.limpaConsole();
			switch (resposta2) {
			case 1:
				System.out.println(Utils.centraliza() + "SAQUE");
				System.out.println("INFORME O VALOR QUE DESEJA SACAR: ");
				double valorSaque = input.nextDouble();
				ArquivoUtils.saque("./saques/", usuario, conta, valorSaque);
				LimpaTela.limpaConsole();
				conta.sacar(valorSaque);
				continuar(usuario, conta, usuarios, contas);
				LimpaTela.limpaConsole();
				break;
			case 2:
				System.out.println(Utils.centraliza() + "DEPÓSITO");
				System.out.println("INFORME O VALOR QUE DESEJA DEPOSITAR: ");
				double valorDeposito = input.nextDouble();
				ArquivoUtils.deposita("./depositos/", usuario, conta, valorDeposito);
				LimpaTela.limpaConsole();
				try {
					conta.depositar(valorDeposito);
				} catch (DepositoNegativoException e) {
					while (valorDeposito <= 0) {
						System.out.println("SOMENTE VALORES POSITIVOS.");
						valorDeposito = input.nextDouble();
						conta.depositar(valorDeposito);
					}
				}
				continuar(usuario, conta, usuarios, contas);
				LimpaTela.limpaConsole();
				break;
			case 3:
				System.out.println(Utils.centraliza() + "TRANSFERÊNCIA");
				System.out.println("INFORME O VALOR QUE DESEJA TRANSFERIR:");

				double valorTransferencia = input.nextDouble();
				LimpaTela.limpaConsole();
				System.out.println("NÚMERO DA CONTA: ");
				input.nextLine();
				String numeroContaDestino = input.nextLine();
				Conta destino = buscaContaDestino(numeroContaDestino, contas);
				ArquivoUtils.transferencia("./transferencias/", usuario, conta, destino, valorTransferencia);
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

	public void relatorios(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas)
			throws IOException {
		System.out.println(
				"[1]-SALDO [2]-TRIBUTAÇÃO DA CONTA CORRENTE [3]-SIMULADOR DE RENDIMENTO DA POUPANÇA [4]-SEGURO DE VIDA [5]-VOLTAR [6]-FINALIZAR");
		int resposta2 = input.nextInt();
		switch (resposta2) {
		case 1:
			ArquivoUtils.saldo("./relatorioSaldo/", usuario, conta);
			LimpaTela.limpaConsole();
			System.out.format("SALDO DA CONTA: %.2f", conta.getSaldo());
			continuar(usuario, conta, usuarios, contas);
			LimpaTela.limpaConsole();
			relatorios(usuario, conta, usuarios, contas);
			break;
		case 2:
			System.out.println("TRIBUTAÇÃO TOTAL DA CONTA CORRENTE ");
			double tributoDeposito = (conta.getQuantidadeDeposito() * Tributos.valorDeposito);
			double tributoSaque = (conta.getQuantidadeSaque() * Tributos.valorSaque);
			double tributoTransferencia = (conta.getQuantidadeTranferencia() * Tributos.valorTransferencia);
			LimpaTela.limpaConsole();
			System.out.println("QUANTIDADE DE SAQUES: " + conta.getQuantidadeSaque());
			System.out.println("QUANTIDADE DE DEPÓSITOS: " + conta.getQuantidadeDeposito());
			System.out.println("QUANTIDADE DE TRANSFERÊNCIAS: " + conta.getQuantidadeTranferencia());
			System.out.println("VALOR POR SAQUE: " + Tributos.valorSaque);
			System.out.println("VALOR POR DEPÓSITO: " + Tributos.valorDeposito);
			System.out.println("VALOR POR SAQUE: " + Tributos.valorTransferencia);
			System.out.format("TOTAL DA TRIBUTAÇÃO: %.2f", (tributoDeposito + tributoSaque + tributoTransferencia));

			ArquivoUtils.tributacaoContaCorrente("./relatoriotributacaoContaCorrente/", usuario, conta);
			continuar(usuario, conta, usuarios, contas);
			LimpaTela.limpaConsole();
			relatorios(usuario, conta, usuarios, contas);
			break;
		case 3:
			LimpaTela.limpaConsole();
			System.out.println("SIMULADOR DO RENDIMENTO DA POUPANÇA\n");
			System.out.print("INFORME O VALOR QUE DESEJA SIMULAR:  ");
			double valor = input.nextDouble();
			System.out.print("INFORME A QUANTIDADE DE DIAS PARA A SIMULAÇÃO: ");
			int dias = input.nextInt();
			LimpaTela.limpaConsole();
			System.out.format("\nO RENDIMENTO DE " + valor + " REAIS POR " + dias + " DIAS SERÁ DE: %.2f REAIS.",
					(Math.pow((1 + ((ContaPoupanca) conta).getRendimento()), dias) * valor));
			ArquivoUtils.relatorioRendimento("./relatorioRendimento/", usuario, conta, valor, dias);
			continuar(usuario, conta, usuarios, contas);
			LimpaTela.limpaConsole();
			relatorios(usuario, conta, usuarios, contas);
			break;
		case 4:
			SeguroVida s = new SeguroVida();
			resposta2 = s.contrataSeguro(usuario, conta, usuarios, contas);
			continuar(usuario, conta, usuarios, contas);
			LimpaTela.limpaConsole();
			break;
		case 5:
			cliente(usuario, conta, usuarios, contas);
			break;
		case 6:
			System.exit(0);
			break;
		}
	}
}