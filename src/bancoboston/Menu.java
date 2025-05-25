package bancoboston;

import bancoboston.managers.BancoManager;
import bancoboston.models.Cliente;
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
			System.out.println("\n\n________________________________________________________");
			System.out.println("\n******** SISTEMA DE GESTION BANCO BOSTON ********\n");
			System.out.println("1 . Registrar cliente");
			System.out.println("2 . Buscar cliente");
			System.out.println("3 . Ver datos del Cliente");
			System.out.println("4 . Depositar");
			System.out.println("5 . Girar");
			System.out.println("6 . Consultar Saldo");
			System.out.println("7 . Salir");
			System.out.println("\nIngrese la opción deseada: ");

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
			case 7 -> System.out.println("\nGracias por su visita a Banco Boston!");
			default -> System.out.println("\n\nError: La opción ingresada no es válida!");
			}
		} while (opcion != 7);
	}

	private void registrarCliente() {
		System.out.println("\n\n________________________________________________________");
		System.out.println("\n******** REGISTRAR CLIENTE ********\n");
		System.out.println("Ingrese el Rut: ");
		String rut = scanner.nextLine();
		
		if (!ValidarRut.esValido(rut)) {
		    System.out.println("\n\nError: El Rut ingresado no es válido!");
		    return;
		}
		
		if (bancoManager.buscarCliente(rut) != null) {
			System.out.println("\n\nError: El rut ingresado, ya se encuentra en nuestros registros!");
			return;
		}

		System.out.println("\nIngrese el nombre: ");
		String nombre = scanner.nextLine();
		System.out.println("\nIngrese el apellido paterno: ");
		String apellidoPaterno = scanner.nextLine();
		System.out.println("\nIngrese el apellido materno: ");
		String apellidoMaterno = scanner.nextLine();
		System.out.println("\nIngrese el domicilio: ");
		String domicilio = scanner.nextLine();
		System.out.println("\nIngrese la comuna: ");
		String comuna = scanner.nextLine();
		System.out.println("\nIngrese el número de teléfono: ");
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
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void buscarCliente() {
		System.out.println("\n\n________________________________________________________");
		System.out.println("\n******** BUSCAR CLIENTE ********\n");
		System.out.println("Ingrese el rut a buscar: ");
		String rut = scanner.nextLine();

		Cliente cliente = bancoManager.buscarCliente(rut);

		if (cliente != null) {
			System.out.println("\n\n________________________________________________________");
			cliente.mostrarInformacionCliente();
			clienteActual = cliente;
		} else {
			System.out.println("\n\nError: No se encontró un cliente con el rut ingresado!\n");
		}
	}

	private void verDatosCliente() {
		if (clienteActual == null) {
			System.out.println("\n\nError: No hay cliente seleccionado o registrado!\n");
			return;
		}
		System.out.println();
		clienteActual.mostrarInformacionCliente();
	}

	private void depositarDinero() {
		if (clienteActual == null) {
			System.out.println("\nError: No hay cliente seleccionado o registrado!\n");
			return;
		}
		System.out.println("Ingrese monto a depositar: ");
		try {
			int monto = Integer.parseInt(scanner.nextLine());
			if (monto <= 0) {
				System.out.println("Error: El monto debe ser mayor a cero!");
				return;
			}
			if (clienteActual.getCuenta().depositar(monto)) {
				System.out.println("Depósito realizado con éxito!");
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: El monto ingresado no es válido!");
		}
	}

	private void girarDinero() {
		if (clienteActual == null) {
			System.out.println("\nError: No hay cliente seleccionado o registrado!\n");
			return;
		}
		System.out.println("Ingrese monto a girar: ");
		try {
			int monto = Integer.parseInt(scanner.nextLine());
			if (monto <= 0) {
				System.out.println("Error: El monto debe ser mayor a cero!");
				return;
			}
			if (clienteActual.getCuenta().girar(monto)) {
				System.out.println("Giro realizado con éxito!");
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: El monto ingresado no es válido!");
		}
	}

	private void consultarSaldo() {
		if (clienteActual == null) {
			System.out.println("\nError: No hay cliente seleccionado o registrado!\n");
			return;
		}
		int saldo = clienteActual.getCuenta().consultarSaldo();
		System.out.println("Saldo actual: $" + String.format("%,d", saldo).replace(',', '.'));
	}

}