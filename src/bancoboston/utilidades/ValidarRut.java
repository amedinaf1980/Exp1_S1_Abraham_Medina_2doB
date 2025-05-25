package bancoboston.utilidades;

public class ValidarRut {
    public static boolean esValido(String rut) {
        if (rut == null || rut.isEmpty()) {
            return false;
        }
        return rut.length() >= 11 && rut.length() <= 12;
    }
}