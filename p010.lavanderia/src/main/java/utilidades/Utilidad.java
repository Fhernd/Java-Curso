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
}
