package br.com.serratec.javaFinal;

import java.io.IOException;

import br.com.serratec.javaFinal.menus.Menu;
import br.com.serratec.javaFinal.usuarios.Cliente;

public class SistemaInterno {
	public static void main(String[] args) throws IOException {
		Cliente c = new Cliente("nome", "cpf", "senha", "cliente");
		Menu menu = new Menu(c);
		
		String tipo= c.getTipo();
		switch (tipo) {
		case ("cliente"):
			menu.cliente(c);
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}
	}

}
