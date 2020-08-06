package contas;

public class ContaCorrenteEspecial extends ContaCorrente {

	private boolean cartaoDeCredito;
	private boolean investimento;
	
	public ContaCorrenteEspecial() {
		super();
	}

	public ContaCorrenteEspecial(int numero, String titular, double saldo, double tarifa, double limite, boolean cartaoDeCredito, boolean investimento) {
		super(numero, titular, saldo, tarifa, limite);
		this.cartaoDeCredito = cartaoDeCredito;
		this.investimento = investimento;
	}

	public boolean isCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(boolean cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}

	public boolean isInvestimento() {
		return investimento;
	}

	public void setInvestimento(boolean investimento) {
		this.investimento = investimento;
	}

}
