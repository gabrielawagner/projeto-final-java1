package br.com.serratec.javaFinal.contaBancaria;

public abstract class Conta {

	private int agencia;
	private String cpfTitular;
	private double saldo;
	private String tipoConta;

	public Conta() {
		super();

	}

	public Conta(int agencia, String cpfTitular, double saldo) {
		super();
		this.agencia = agencia;
		this.cpfTitular = cpfTitular;
		this.saldo = saldo;
	}

	public abstract boolean sacar(double valor);

	public abstract boolean transfere(Conta destino, double valor);

	public abstract void depositar(double valor);

	public abstract void depositarDeTransferencia(double valor);

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

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

}
