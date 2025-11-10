package exception;

public class LivroNaoDisponivelException extends Exception {
    
    public LivroNaoDisponivelException(String message) {
        super(message);
    }
    
    public LivroNaoDisponivelException(String message, Throwable cause) {
        super(message, cause);
    }
}
