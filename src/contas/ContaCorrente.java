package contas;

public class ContaCorrente implements contaTributavel {

	//Atributos
	protected int numero;
	protected String titular;
	protected double saldo;
	protected double tarifa;
	protected double limite;
	static int numeroContas;
	
	//Construtores
	public ContaCorrente() {numeroContas++;}
	public ContaCorrente(int numero, String titular, double saldo, double tarifa, double limite) {
		this.numero = numero;
		this.titular = titular;
		this.saldo = saldo;
		this.tarifa = tarifa;
		this.limite = limite;
		numeroContas++;
	}
	
	@Override
	public boolean sacar(double valor) {
		if (this.saldo < valor + tributo) {
			return false;
		} 
		else {
			double novoSaldo = this.saldo - valor - tributo;
			this.saldo = novoSaldo;
			return true;
		}
	}
	
	@Override
	public boolean transfere(Conta destino, double valor) {
		boolean retirou = this.sacar(valor + tributo);
		if (retirou == false) {
			return false;
		}
		else {
			destino.depositarDeTransferencia(valor);
			return true;
		}
	}
	
	@Override
	public void depositar(double valor) {
		this.saldo = this.saldo + valor - tributo;
	}
	
	@Override
	public void depositarDeTransferencia(double valor) {
		this.saldo = this.saldo + valor;
	}

	//getters e setters
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

	public double getTarifa() {
		return tarifa;
	}

	public void setTarifa(double tarifa) {
		this.tarifa = tarifa;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

}
