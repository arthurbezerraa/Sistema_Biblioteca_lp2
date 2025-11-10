package exception;

public class UsuarioNaoEncontradoException extends Exception {
    
    public UsuarioNaoEncontradoException(String message) {
        super(message);
    }
    
    public UsuarioNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
