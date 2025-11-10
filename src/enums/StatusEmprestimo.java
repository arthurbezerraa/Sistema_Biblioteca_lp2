package enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum StatusEmprestimo {
    ATIVO("Empréstimo ativo"),
    DEVOLVIDO("Livro devolvido"),
    ATRASADO("Empréstimo em atraso");
    
    private final String descricao;
    
    StatusEmprestimo(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public boolean podeTransitarPara(StatusEmprestimo novoStatus) {
        Objects.requireNonNull(novoStatus, "Novo status não pode ser nulo");
        
        if (this == novoStatus) {
            return true;
        }
        
        switch (this) {
            case ATIVO:
                return novoStatus == DEVOLVIDO || novoStatus == ATRASADO;
            case ATRASADO:
                return novoStatus == DEVOLVIDO;
            case DEVOLVIDO:
                return false;
            default:
                return false;
        }
    }
    
    public List<StatusEmprestimo> getTransicoesValidas() {
        return Arrays.stream(values())
            .filter(this::podeTransitarPara)
            .collect(Collectors.toList());
    }
    
    public boolean isAtivo() {
        return this == ATIVO;
    }
    
    public boolean isAtrasado() {
        return this == ATRASADO;
    }
    
    public boolean isDevolvido() {
        return this == DEVOLVIDO;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s", name(), descricao);
    }
}
