package br.com.serratec.javaFinal.contaBancaria;

import java.text.DecimalFormat;

public class ContaPoupanca extends Conta implements Tributos{
	
	private final double rendimento = 0.0016;
		
	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(String agencia, String numero, String cpfTitular, double saldo, String tipo) {
		super(agencia, numero, cpfTitular, saldo, tipo);
	}
	
	public double getRendimento() {
		return rendimento;
	}

	@Override
	public boolean sacar(double valor) {
		//DecimalFormat df = new DecimalFormat("#.##");
		if (this.getSaldo() < valor + valorSaque) {
			System.out.println("Saldo indisponivel!");
			return false;
		} else {
			executaOperacao("sacar");
			double novoSaldo = this.getSaldo() - valor - valorSaque;
			System.out.format("Valor debitado da sua conta: %.2f", (valor + valorSaque));
			this.setSaldo(novoSaldo);
			System.out.format("Saldo disponivel: %.2f", this.getSaldo());
			return true;
		}
	}

	@Override
	public boolean transfere(Conta destino, double valor) {
		if (this.sacarTransferencia(valor + valorTransferencia)) {
			executaOperacao("transfere");
			destino.depositarDeTransferencia(valor);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void depositar(double valor) {
		System.out.println("Valor depositado: " + valor);
		System.out.format("Novo saldo: %.2f", (this.getSaldo() + valor - valorDeposito));
		executaOperacao("depositar");
		this.setSaldo(this.getSaldo() + valor - valorDeposito);
	}

	@Override
	public void depositarDeTransferencia(double valor) {
		this.setSaldo(this.getSaldo() + valor);
	}

	public boolean sacarTransferencia(double valor) {
		if (this.getSaldo() < valor) {
			System.out.println("Saldo indisponivel!");
			return false;
		} else {
			double novoSaldo = this.getSaldo() - valor;
			System.out.println("Valor debitado da sua conta: " + (valor));
			this.setSaldo(novoSaldo);
			System.out.format("Saldo disponivel: %.2f", this.getSaldo());
			return true;
		}
	}
	
}