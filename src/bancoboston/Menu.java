package bancoboston;

import bancoboston.managers.BancoManager;
import bancoboston.models.Cliente;
import bancoboston.utilidades.Grafico;
import bancoboston.utilidades.ValidarRut;

import java.util.Scanner;

public class Menu {

	private Scanner scanner;
	private BancoManager bancoManager;
	private Cliente clienteActual;

	public Menu() {
		scanner = new Scanner(System.in);
		bancoManager = BancoManager.getInstancia();
		clienteActual = null;
	}

	public void mostrarMenu() {
		int opcion;
		do {
			Grafico.formatoTitulo("******** SISTEMA DE GESTION BANCO BOSTON ********");
			System.out.println("1 . Registrar cliente");
			System.out.println("2 . Buscar cliente");
			System.out.println("3 . Ver datos del Cliente");
			System.out.println("4 . Depositar");
			System.out.println("5 . Girar");
			System.out.println("6 . Consultar Saldo");
			System.out.println("7 . Salir");
			Grafico.formatoIngresoDatos("\nIngrese la opción deseada: ");

			try {
				opcion = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				opcion = 0;
			}

			switch (opcion) {
			case 1 -> registrarCliente();
			case 2 -> buscarCliente();
			case 3 -> verDatosCliente();
			case 4 -> depositarDinero();
			case 5 -> girarDinero();
			case 6 -> consultarSaldo();
			case 7 -> Grafico.formatoSaludo("Gracias por su visita a Banco Boston!");
			default -> Grafico.formatoError("Error: La opción ingresada no es válida!");
			}
		} while (opcion != 7);
	}

	private void registrarCliente() {
		Grafico.formatoTitulo("******** REGISTRAR CLIENTE ********");
		Grafico.formatoIngresoDatos("Ingrese el Rut: ");
		String rut = scanner.nextLine();
		
		if (!ValidarRut.esValido(rut)) {
			Grafico.formatoError("Error: El Rut ingresado no es válido!");
		    return;
		}
		
		if (bancoManager.buscarCliente(rut) != null) {
			Grafico.formatoError("Error: El rut ingresado, ya se encuentra en nuestros registros!");
			return;
		}

		Grafico.formatoIngresoDatos("Ingrese el nombre: ");
		String nombre = scanner.nextLine();
		Grafico.formatoIngresoDatos("Ingrese el apellido paterno: ");
		String apellidoPaterno = scanner.nextLine();
		Grafico.formatoIngresoDatos("Ingrese el apellido materno: ");
		String apellidoMaterno = scanner.nextLine();
		Grafico.formatoIngresoDatos("Ingrese el domicilio: ");
		String domicilio = scanner.nextLine();
		Grafico.formatoIngresoDatos("Ingrese la comuna: ");
		String comuna = scanner.nextLine();
		Grafico.formatoIngresoDatos("Ingrese el número de teléfono: ");
		int telefono = scanner.nextInt();
		scanner.nextLine();
		int numeroCuenta = Integer.parseInt(rut.replaceAll("[^0-9]", ""));
		try {
			Cliente nuevoCliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna,
					telefono, numeroCuenta);
			if (nuevoCliente.registrarCliente() && bancoManager.agregarCliente(nuevoCliente)) {
				clienteActual = nuevoCliente;
			}
		} catch (IllegalArgumentException e) {
			Grafico.formatoError("Error: " + e.getMessage());
		}
	}

	private void buscarCliente() {
		Grafico.formatoTitulo("******** BUSCAR CLIENTE ********");
		Grafico.formatoIngresoDatos("Ingrese el rut a buscar: ");
		String rut = scanner.nextLine();

		Cliente cliente = bancoManager.buscarCliente(rut);

		if (cliente != null) {
			Grafico.lineaSeparacion();
			cliente.mostrarInformacionCliente();
			clienteActual = cliente;
		} else {
			Grafico.formatoError("Error: No se encontró un cliente con el rut ingresado!");
		}
	}

	private void verDatosCliente() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			return;
		}
		System.out.println();
		clienteActual.mostrarInformacionCliente();
	}

	private void depositarDinero() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			return;
		}
		Grafico.formatoIngresoDatos("Ingrese monto a depositar: ");
		try {
			int monto = Integer.parseInt(scanner.nextLine());
			if (monto <= 0) {
				Grafico.formatoError("Error: El monto debe ser mayor a cero!");
				return;
			}
			if (clienteActual.getCuenta().depositar(monto)) {
				Grafico.formatoExito("Depósito realizado con éxito!");
			}
		} catch (NumberFormatException e) {
			Grafico.formatoError("Error: El monto ingresado no es válido!");
		}
	}

	private void girarDinero() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			return;
		}
		Grafico.formatoIngresoDatos("Ingrese monto a girar: ");
		try {
			int monto = Integer.parseInt(scanner.nextLine());
			if (monto <= 0) {
				Grafico.formatoError("Error: El monto debe ser mayor a cero!");
				return;
			}
			if (clienteActual.getCuenta().girar(monto)) {
				Grafico.formatoExito("Giro realizado con éxito!");
			}
		} catch (NumberFormatException e) {
			Grafico.formatoError("Error: El monto ingresado no es válido!");
		}
	}

	private void consultarSaldo() {
		if (clienteActual == null) {
			Grafico.formatoError("Error: No hay cliente seleccionado o registrado!");
			return;
		}
		int saldo = clienteActual.getCuenta().consultarSaldo();
		Grafico.formatoExito("Saldo actual: $" + String.format("%,d", saldo).replace(',', '.'));
	}

}