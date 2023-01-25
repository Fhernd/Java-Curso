package utilidades;

import com.password4j.Hash;
import com.password4j.Password;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     * Encripta una contraseña utilizando el algoritmo PBKDF2.
     *
     * @param password Contraseña a encriptar.
     * @return Contraseña encriptada.
     */
    public static String encriptar(String password) {
        Hash hash = Password.hash(password).addSalt("@#123-@").withPBKDF2();

        return hash.getResult();
    }

    public static boolean comprobarPassword(String clave, String password) {
        return Password.check(password, clave).addSalt("@#123-@").withPBKDF2();
    }

    /**
     * Convertir un objeto LocalDateTime a una cadena de caracteres.
     *
     * @param fecha Fecha a convertir.
     *              No puede ser nula.
     * @return Cadena de caracteres con la fecha.
     */
    public static String fechaToString(LocalDateTime fecha) {
        return fecha.format(DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm"));
    }
}
