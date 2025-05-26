package bancoboston.utilidades;

import java.text.DecimalFormat;

public class Grafico {

	// ************ COLORES CON FONDO ************
	
	private final static String CYAN_NEGRO = "\u001B[36m\u001B[40m\u001B[1m";
	private final static String VERDE_NEGRO = "\u001B[32m\u001B[40m\u001B[1m";
	private final static String ROJO_NEGRO = "\u001B[31m\u001B[40m\u001B[1m";
	private final static String NEGRO_VERDE = "\u001B[30m\u001B[42m\u001B[1m";
	private final static String RESET_COLOR = "\u001B[0m";
	
	// ************ COLORES TEXTO SIMPLE ************
	private final static String AMARILLO = "\u001B[33m";
	
	private final static int ANCHO_CONSOLA = 24;
	
	private final static String LINEA_CONTINUA = "_".repeat(ANCHO_CONSOLA * 2);
	private final static String LINEA_SEGMENTADA = "-".repeat(ANCHO_CONSOLA);
	
	public final static DecimalFormat FORMATO_DINERO = new DecimalFormat("$###,###,###");
	
	public static void colorTextoConSalto(String mensaje, String color) {
		System.out.println(color + mensaje + RESET_COLOR);
	}
	public static void lineaSeparacion() {
		System.out.println("\n\n" + LINEA_CONTINUA);
		System.out.println(LINEA_SEGMENTADA + "\n");
	}
	
	public static void formatoSaludo(String titulo) {
		colorTextoConSalto("\n\n" + titulo, VERDE_NEGRO);
	}
	
	public static void formatoTitulo(String titulo) {
		Grafico.lineaSeparacion();
		colorTextoConSalto(titulo + "\n", CYAN_NEGRO);
	}
	
	public static void formatoIngresoDatos(String datos) {
		colorTextoConSalto(datos, AMARILLO);
	}
	
	public static void formatoError(String error) {
		Grafico.lineaSeparacion();
		colorTextoConSalto(error + "\n", ROJO_NEGRO);
	}
	
	public static void formatoExito(String exito) {
		Grafico.lineaSeparacion();
		colorTextoConSalto(exito + "\n", NEGRO_VERDE);
	}
}