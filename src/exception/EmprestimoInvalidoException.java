package exception;

public class EmprestimoInvalidoException extends Exception {
    
    public EmprestimoInvalidoException(String message) {
        super(message);
    }
    
    public EmprestimoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
