package exception;

public class UsuarioJaExisteException extends Exception {
    
    public UsuarioJaExisteException(String message) {
        super(message);
    }
    
    public UsuarioJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}
