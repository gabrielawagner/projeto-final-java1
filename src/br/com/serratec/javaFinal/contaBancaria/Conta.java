package br.com.serratec.javaFinal.contaBancaria;

public abstract class Conta {

	private int agencia;
	private String cpfTitular;
	private double saldo;
	private String tipoConta;
	private int quantidadeTranferencia, quantidadeSaque, quantidadeDeposito;

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
	
	public int getQuantidadeTranferencia() {
		return quantidadeTranferencia;
	}

	public void setQuantidadeTranferencia(int quantidadeTranferencia) {
		this.quantidadeTranferencia = quantidadeTranferencia;
	}

	public int getQuantidadeSaque() {
		return quantidadeSaque;
	}

	public void setQuantidadeSaque(int quantidadeSaque) {
		this.quantidadeSaque = quantidadeSaque;
	}

	public int getQuantidadeDeposito() {
		return quantidadeDeposito;
	}

	public void setQuantidadeDeposito(int quantidadeDeposito) {
		this.quantidadeDeposito = quantidadeDeposito;
	}

	public void executaOperacao(String tipo) {
		switch (tipo) {
		case "depositar": 
			quantidadeDeposito++;
			break;
		case "transfere": 
			quantidadeTranferencia++;
			break;
		case "sacar": 
			quantidadeSaque++;
			break;
		}
	}

}
