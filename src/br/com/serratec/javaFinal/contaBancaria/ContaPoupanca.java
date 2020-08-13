package br.com.serratec.javaFinal.contaBancaria;

import br.com.serratec.javaFinal.exceptions.DepositoNegativoException;

public class ContaPoupanca extends Conta implements Tributos {

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
		if (this.getSaldo() < valor + valorSaque) {
			System.out.println("SALDO INDISPONÍVEL!");
			return false;
		} else {
			executaOperacao("sacar");
			double novoSaldo = this.getSaldo() - valor - valorSaque;
			System.out.println("VALOR DEBITADO DA SUA CONTA: " + (valor + valorSaque));
			this.setSaldo(novoSaldo);
			System.out.format("\nSALDO DISPONÍVEL: %.2f", this.getSaldo());
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
			System.out.println("VALOR DEPOSITADO: " + valor);
			System.out.format("\nNOVO SALDO: %.2f", (this.getSaldo() + valor - valorDeposito));
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
			System.out.println("SALDO INDISPONÍVEL!");
			return false;
		} else {
			double novoSaldo = this.getSaldo() - valor;
			System.out.println("\nVALOR DEBITADO DA SUA CONTA: " + (valor));
			this.setSaldo(novoSaldo);
			System.out.format("\nSALDO DISPONÍVEL: %.2f", this.getSaldo());
			return true;
		}
	}

}