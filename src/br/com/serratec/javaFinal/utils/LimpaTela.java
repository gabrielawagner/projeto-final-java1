package br.com.serratec.javaFinal.utils;

public class LimpaTela {
	public final static void clearConsole()
	{
	    try
	    {
	    	//new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	    	Runtime.getRuntime().exec("cmd /c cls");
	    }
	    catch (final Exception e)
	    {
	        e.printStackTrace();
	    }
	}
}
