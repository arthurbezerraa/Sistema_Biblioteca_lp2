package enums;

public enum TemaInterface {
    CLARO("Claro", "\033[0m", "\033[30m"),
    ESCURO("Escuro", "\033[0m", "\033[37m");

    private final String nome;
    private final String codigoCor;
    private final String corTexto;

    TemaInterface(String nome, String codigoCor, String corTexto) {
        this.nome = nome;
        this.codigoCor = codigoCor;
        this.corTexto = corTexto;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoCor() {
        return codigoCor;
    }

    public String getCorTexto() {
        return corTexto;
    }

    public String aplicarTema(String texto) {
        return codigoCor + corTexto + texto + "\033[0m";
    }

    public TemaInterface alternar() {
        return this == CLARO ? ESCURO : CLARO;
    }

    public static TemaInterface fromString(String str) {
        if (str == null || str.trim().isEmpty()) {
            return CLARO;
        }

        try {
            return TemaInterface.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CLARO;
        }
    }

    @Override
    public String toString() {
        return nome;
    }
}