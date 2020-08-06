package contaBancaria;

public abstract class Conta {
	
	private int agencia;
	private String cpfTitular;
	private double saldo;
	
	
	public Conta() {
		super();
		
	}	
	
	public Conta(int agencia, String cpfTitular, double saldo) {
		super();
		this.agencia = agencia;
		this.cpfTitular = cpfTitular;
		this.saldo = saldo;
	}
	
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public String getCpfTitular() {
		return cpfTitular;
	}
	public void setCpfTitular(String cpfTitular) {
		this.cpfTitular = cpfTitular;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	
	
	
	
	
	
	
	
	

}
