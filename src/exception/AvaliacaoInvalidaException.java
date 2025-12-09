package exception;

public class AvaliacaoInvalidaException extends Exception {

    public AvaliacaoInvalidaException(String mensagem) {
        super(mensagem);
    }

    public AvaliacaoInvalidaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}