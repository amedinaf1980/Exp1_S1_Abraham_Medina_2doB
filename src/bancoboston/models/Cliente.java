package bancoboston.models;

import bancoboston.BancoBoston;

public class Cliente {
	private String rut;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String domicilio;
	private String comuna;
	private int telefono;
	private int numeroCuenta;
	private Cuenta cuenta;

	public Cliente(String rut, String nombre, String apellidoPaterno, String apellidoMaterno, String domicilio,
			String comuna, int telefono, int numeroCuenta) {
		this.rut = rut;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.domicilio = domicilio;
		this.comuna = comuna;
		this.telefono = telefono;
		this.numeroCuenta = numeroCuenta;
		this.cuenta = new Cuenta(numeroCuenta, 0);
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public boolean registrarCliente() {
			System.out.println("\n\n________________________________________________________");
			System.out.println("\nCliente registrado correctamente:\n" + "\nRut      : " + this.rut + "\nNombre   : "
					+ nombre + " " + apellidoPaterno + " " + apellidoMaterno + "\nDomicilio: " + domicilio + ", "
					+ comuna + "\nTeléfono : " + telefono + "\nN° Cuenta: " + cuenta.getNumeroCuenta() + "\nSaldo    : "
					+ BancoBoston.FORMATO_DINERO.format(cuenta.getSaldo()));
			return true;
	}

	public void mostrarInformacionCliente() {
		System.out.println("\n\n________________________________________________________");
		System.out.println("\n******** INFORMACION DEL CLIENTE ********\n");
		System.out.println("Rut      : " + this.rut);
		System.out.println("Nombre   : " + nombre + " " + apellidoPaterno + " " + apellidoMaterno);
		System.out.println("Docimilio: " + domicilio + ", " + comuna);
		System.out.println("Teléfono : " + telefono);
		System.out.println("N° Cuenta: " + cuenta.getNumeroCuenta());
		System.out.println("Saldo    : " + BancoBoston.FORMATO_DINERO.format(cuenta.getSaldo()));
	}

	public String getRut() {
		return this.rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
}