package br.com.serratec.javaFinal.contaBancaria;

import java.util.List;
import java.util.Scanner;

import br.com.serratec.javaFinal.usuarios.Cliente;
import br.com.serratec.javaFinal.usuarios.Usuario;

public class SeguroVida {

	String cpfClienteSeguro;
	
	public int contrataSeguro(Usuario usuario, Conta conta, List<Usuario> usuarios, List<Conta> contas) {
		Cliente cliente = (Cliente)usuario;
		System.out.println("Insira o valor que deseja assegurar: ");
		Scanner input = new Scanner(System.in);
		double valorSeguro = input.nextDouble();
		while(valorSeguro > conta.getSaldo() || valorSeguro < 0) {
			System.out.println("Valor Invalido, Insira um valor que você possua em conta");
			valorSeguro = input.nextDouble();
			input.close();
		}
		cliente.setTemSeguro(true);
		conta.setSaldo(conta.getSaldo() - valorSeguro*Tributos.seguroTributo);
		System.out.println("Novo saldo: " + conta.getSaldo());
		return 2;
	}

}
