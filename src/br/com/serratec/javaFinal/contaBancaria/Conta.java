package br.com.serratec.javaFinal.contaBancaria;

public abstract class Conta {

	private String agencia;
	private String numero;
	private String cpfTitular;
	private String tipo;
	private double saldo;

	private int quantidadeTranferencia, quantidadeSaque, quantidadeDeposito;

	public Conta() {
		super();

	}
	
	public Conta(String agencia, String numero, String cpfTitular, double saldo, String tipo) {
		this.agencia = agencia;
		this.numero = numero;
		this.cpfTitular = cpfTitular;
		this.saldo = saldo;
		this.tipo = tipo;
	}

	public abstract boolean sacar(double valor);

	public abstract boolean transfere(Conta destino, double valor);

	public abstract void depositar(double valor);

	public abstract void depositarDeTransferencia(double valor);

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
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
		return tipo;
	}

	public void setTipoConta(String tipoConta) {
		this.tipo = tipoConta;
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
