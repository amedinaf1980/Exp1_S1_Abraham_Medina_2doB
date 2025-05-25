package bancoboston.models;

public class Cuenta {
	private int numeroCuenta;
	private int saldo;

	public Cuenta(int numeroCuenta, int saldo) {
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
	}

	public void mostrarInformacionCuenta() {
		System.out.println("N° Cuenta: " + this.numeroCuenta);
		System.out.println("Saldo    : " + this.saldo);
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public boolean depositar(int monto) {
		if (monto > 0) {
			saldo += monto;
			return true;
		}
		System.out.println("Error: El valor ingreaado no es válido.");
		return false;
	}

	public boolean girar(int monto) {
		if (monto <= 0) {
			System.out.println("Error: El valor ingreaado no es válido.");
			return false;
		}
		if (saldo >= monto) {
			saldo -= monto;
			return true;
		} else {
			System.out.println("Error: Saldo insuficiente para realizar el giro!");
			return false;
		}
	}

	public int consultarSaldo() {
		return saldo;
	}
}