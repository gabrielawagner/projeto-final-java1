package src.contaBancaria;

import menus.Login;
import pessoal.Gerente;

public class SistemaInterno {

	public static void main(String[] args) {
		Gerente g = new Gerente("111.222.333-44", "123456", "Cliente");
		Login j = new Login();
		String tipo;
		tipo = g.getCargo();
		switch (tipo) {
		case ("Cliente"):
			j.LogCliente();
			break;

		default:
			throw new IllegalArgumentException("Unexpected value: ");
		}

	}

}
