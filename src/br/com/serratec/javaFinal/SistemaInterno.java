package br.com.serratec.javaFinal;

import br.com.serratec.javaFinal.menus.Menu;
import br.com.serratec.javaFinal.usuarios.Cliente;

public class SistemaInterno {
	public static void main(String[] args) {
		Cliente c = new Cliente("Eclipse","111.222.333-44", "Banana", false, "Cliente");
		Menu menu = new Menu();
//		String tipo;
//		tipo = g.getCargo();
//		switch (tipo) {
//		case ("Cliente"):
//			menu.cliente();
//			break;
//
//		default:
//			throw new IllegalArgumentException("Unexpected value: ");
//		}
		
		menu.principal(c);
	}

}
