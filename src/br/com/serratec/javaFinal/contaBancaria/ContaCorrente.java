package br.com.serratec.javaFinal.contaBancaria;

public class ContaCorrente extends Conta implements Tributos {

	public ContaCorrente() {
		super();
	}

	public ContaCorrente(int agencia, String cpfTitular, double saldo) {
		super(agencia, cpfTitular, saldo);
	}

	public boolean sacar(double valor) {
		if (this.getSaldo() < valor + saqueTributo) {
			System.out.println("Saldo indisponivel!");
			return false;
		} else {
			executaOperacao("sacar");
			double novoSaldo = this.getSaldo() - valor - saqueTributo;
			System.out.println("Valor debitado da sua conta: " + (valor + saqueTributo));
			this.setSaldo(novoSaldo);
			System.out.println("Saldo disponivel: " + this.getSaldo());
			return true;
		}
	}

	public boolean transfere(Conta destino, double valor) {
		if (this.sacarTransferencia(valor + transferenciaTributo)) {
			// TODO Implementar exceção
			executaOperacao("transfere");
			destino.depositarDeTransferencia(valor);
			return true;
		} else {
			return false;
		}
	}

	public void depositar(double valor) {
		System.out.println("Valor depositado: " + valor);
		System.out.println("Novo saldo: " + (this.getSaldo() + valor - depositoTributo));
		executaOperacao("depositar");
		this.setSaldo(this.getSaldo() + valor - depositoTributo);
	}

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
			System.out.println("Saldo disponivel: " + this.getSaldo());
			return true;
		}
	}
}
