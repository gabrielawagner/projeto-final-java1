package br.com.serratec.javaFinal.contaBancaria;

import br.com.serratec.javaFinal.exceptions.DepositoNegativoException;

public class ContaCorrente extends Conta implements Tributos {

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(String agencia, String numero, String cpfTitular, double saldo, String tipo) {
		super(agencia, numero, cpfTitular, saldo, tipo);
	}

	@Override
	public boolean sacar(double valor) {
		if (this.getSaldo() < valor + valorSaque) {
			System.out.println("Saldo indisponivel!");
			return false;
		} else {
			executaOperacao("sacar");
			double novoSaldo = this.getSaldo() - valor - valorSaque;
			System.out.println("Valor debitado da sua conta: " + (valor + valorSaque));
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
	public void depositar(double valor) throws DepositoNegativoException {
		if (valor < 0) {
			throw new DepositoNegativoException();
		} else {
			System.out.println("Valor depositado: " + valor);
			System.out.format("Novo saldo: %.2f", (this.getSaldo() + valor - valorDeposito));
			executaOperacao("depositar");
			this.setSaldo(this.getSaldo() + valor - valorDeposito);
		}
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