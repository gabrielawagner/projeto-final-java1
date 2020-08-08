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
			double novoSaldo = this.getSaldo() - valor - saqueTributo;
			System.out.println("Valor debitado da sua conta: " + (valor + saqueTributo));
			this.setSaldo(novoSaldo);
			System.out.println("Saldo disponivel: " + this.getSaldo());
			return true;
		}
	}

	public boolean transfere(Conta destino, double valor) {
		boolean retirou = this.sacar(valor + transferenciaTributo);
		if (retirou == false) { // TODO Implementar exceção
			return false;
		} else {
			destino.depositarDeTransferencia(valor);
			return true;
		}
	}

	public void depositar(double valor) {
		System.out.println("Valor depositado: " + valor);
		System.out.println("Novo saldo: " + (this.getSaldo() + valor - depositoTributo));
		this.setSaldo(this.getSaldo() + valor - depositoTributo);
	}

	public void depositarDeTransferencia(double valor) {
		this.setSaldo(this.getSaldo() + valor);

	}

}
