package contaBancaria;

public class ContaCorrente extends Conta {
	
	private String tipoConta;

	
	public ContaCorrente() {
		super();
		
	}

	public ContaCorrente(int agencia, String cpfTitular, double saldo, String tipoConta) {
		super(agencia, cpfTitular, saldo);
		this.tipoConta = tipoConta;
	}

	
	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	
	
	
	

}
