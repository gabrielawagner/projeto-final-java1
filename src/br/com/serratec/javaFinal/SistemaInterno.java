package br.com.serratec.javaFinal;

import br.com.serratec.javaFinal.menus.Menu;
import br.com.serratec.javaFinal.pessoal.Gerente;

public class SistemaInterno {

	public static void main(String[] args) {
		Gerente g = new Gerente("111.222.333-44", "123456", "Cliente");
		Menu menu = new Menu();
		String tipo;
		tipo = g.getCargo();
		switch (tipo) {
		case ("Cliente"):
			menu.cliente();
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}

	}

}
