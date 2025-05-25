package bancoboston;

import java.text.DecimalFormat;

import bancoboston.managers.BancoManager;

public class BancoBoston {
	public final static DecimalFormat FORMATO_DINERO = new DecimalFormat("$###,###,###");

	public static void main(String[] args) {
		System.out.println("\n________________________________________________________\n\n");
		System.out.println("         !!! BIENVENID@S A BANCO BOSTON !!!");

		// ************* COMENTAR SI NO QUIERO DATOS AL INICIO *************
        BancoManager.getInstancia().cargarDatosIniciales();
        
		Menu menu = new Menu();
		menu.mostrarMenu();
	}
}