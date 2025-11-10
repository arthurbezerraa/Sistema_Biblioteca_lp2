package exception;

public class LivroJaExisteException extends Exception {
    
    public LivroJaExisteException(String message) {
        super(message);
    }
    
    public LivroJaExisteException(String message, Throwable cause) {
        super(message, cause);
    }
}
