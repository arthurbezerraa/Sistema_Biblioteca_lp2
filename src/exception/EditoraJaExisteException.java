package exception;

public class EditoraJaExisteException extends Exception {

    public EditoraJaExisteException(String mensagem) {
        super(mensagem);
    }

    public EditoraJaExisteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}