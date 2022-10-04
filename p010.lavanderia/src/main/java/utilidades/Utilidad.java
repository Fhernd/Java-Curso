package utilidades;

import org.apache.commons.validator.routines.EmailValidator;

public class Utilidad {
    /**
     * Validar si una cadena de caracteres es un email válido.
     *
     * @param email Email a validar.
     * @return true si es válido, false en caso contrario.
     */
    public static boolean validarEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    /**
     * Comprueba si una contraseña es válida.
     *
     * @param password Contraseña a validar.
     *                 Debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número.
     *                 Puede contener caracteres especiales.
     *                 No puede contener espacios.
     *                 No puede ser nula.
     *                 No puede ser vacía.
     *                 No puede ser una cadena de espacios.
     * @return true si es válida, false en caso contrario.
     */
    public static boolean validarPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$");
    }
}
