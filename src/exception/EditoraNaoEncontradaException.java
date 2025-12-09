package exception;

public class EditoraNaoEncontradaException extends Exception {

    public EditoraNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public EditoraNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}