package contaBancaria;

public class ContaPoupanca extends Conta implements Tributos{
	
	private final double rendimento = 0.5;
		
	public ContaPoupanca() {
		super();
		
	}

	public ContaPoupanca(int agencia, String cpfTitular, double saldo) {
		super(agencia, cpfTitular, saldo);
		
	}

	public boolean sacar(double valor) {
		if (this.getSaldo() < valor + saqueTributo) {
			return false;
		} 
		else {
			double novoSaldo = this.getSaldo() - valor - saqueTributo;
			this.setSaldo(novoSaldo);
			return true;
		}
	}
	
	public boolean transfere(Conta destino, double valor) {
		boolean retirou = this.sacar(valor + transferenciaTributo);
		if (retirou == false) { 								//TODO Implementar exceção
			return false;
		}
		else {
			destino.depositarDeTransferencia(valor);
			return true;
		}
	}
	
	public void depositar(double valor) {
		this.setSaldo(this.getSaldo() + valor - depositoTributo);
	}
	
	
	public void depositarDeTransferencia(double valor) {
		this.setSaldo(this.getSaldo() + valor);
	}
	
	public double getRendimento() {
		return rendimento;
	}
	
}
