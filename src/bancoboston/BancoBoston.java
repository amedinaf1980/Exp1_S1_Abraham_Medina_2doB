package bancoboston;


import bancoboston.managers.BancoManager;
import bancoboston.utilidades.Grafico;
public class BancoBoston {

	public static void main(String[] args) {

		Grafico.formatoSaludo("\n\n         !!! BIENVENID@S A BANCO BOSTON !!!         \n\n");

		// ************* COMENTAR SI NO QUIERO DATOS AL INICIO *************
        BancoManager.getInstancia().cargarDatosIniciales();
        
		Menu menu = new Menu();
		menu.mostrarMenu();
	}
}