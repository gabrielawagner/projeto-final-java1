package br.com.serratec.javaFinal.contaBancaria;

import java.util.Scanner;

import br.com.serratec.javaFinal.usuarios.Usuario;

public class SeguroVida {

	String cpfClienteSeguro;
	
	public void contrataSeguro(Usuario u, Conta conta) {
		double valorSeguro;
		System.out.println("Insira o valor que deseja assegurar: ");
		Scanner input = new Scanner(System.in);
		valorSeguro = input.nextDouble();
		u.setTemSeguro(true);
		conta.setSaldo(conta.getSaldo() - valorSeguro*Tributos.seguroTributo);
		System.out.println("Novo saldo: " + conta.getSaldo());
	}

}
