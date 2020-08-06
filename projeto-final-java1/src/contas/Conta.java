package contas;

public interface Conta {
	
	static final int numeroBanco = 237;
	public boolean sacar(double valor);
	public boolean transfere(Conta destino, double valor);
	public void depositar(double valor);
	public void depositarDeTransferencia(double valor);
	
}
