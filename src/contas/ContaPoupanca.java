package contas;

public class ContaPoupanca implements Conta {

	protected int numero;
	protected String titular;
	protected double saldo;
	protected double rendimento;

	public ContaPoupanca() {}
	
	public ContaPoupanca(int numero, String titular, double saldo, double rendimento) {
		super();
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
		this.rendimento = rendimento;
	}

	@Override
	public boolean sacar(double valor) {
		if (this.saldo < valor) {
			return false;
		} 
		else {
			double novoSaldo = this.saldo - valor;
			this.saldo = novoSaldo;
			return true;
		}
	}
	
	@Override
	public boolean transfere(Conta destino, double valor) {
		boolean retirou = this.sacar(valor);
		if (retirou == false) {
			return false;
		}
		else {
			destino.depositar(valor);
			return true;
		}
	}
	
	@Override
	public void depositar(double valor) {
		this.saldo = this.saldo + valor;
	}
	
	@Override
	public void depositarDeTransferencia(double valor) {
		this.saldo = this.saldo + valor;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getRendimento() {
		return rendimento;
	}

	public void setRendimento(double rendimento) {
		this.rendimento = rendimento;
	}
	
}
