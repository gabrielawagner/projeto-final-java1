package br.com.serratec.javaFinal.contaBancaria;

import java.util.Scanner;

import br.com.serratec.javaFinal.usuarios.Cliente;
import br.com.serratec.javaFinal.usuarios.Usuario;

public class SeguroVida {

	String cpfClienteSeguro;
	
	public int contrataSeguro(Usuario usuario, Conta conta) {
		Cliente cliente = (Cliente)usuario;
		System.out.println("Insira o valor que deseja assegurar: ");
		Scanner input = new Scanner(System.in);
		double valorSeguro = input.nextDouble();
		cliente.setTemSeguro(true);
		conta.setSaldo(conta.getSaldo() - valorSeguro*Tributos.seguroTributo);
		System.out.println("Novo saldo: " + conta.getSaldo());
		input.close();
		return 2;
	}

}
