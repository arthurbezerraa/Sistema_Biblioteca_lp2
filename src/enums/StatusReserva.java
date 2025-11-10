package enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public enum StatusReserva {
    ATIVA("Reserva ativa"),
    CANCELADA("Reserva cancelada"),
    CONCLUIDA("Reserva concluída");
    
    private final String descricao;
    
    StatusReserva(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public boolean podeTransitarPara(StatusReserva novoStatus) {
        Objects.requireNonNull(novoStatus, "Novo status não pode ser nulo");
        
        if (this == novoStatus) {
            return true;
        }
        
        switch (this) {
            case ATIVA:
                return novoStatus == CANCELADA || novoStatus == CONCLUIDA;
            case CANCELADA:
            case CONCLUIDA:
                return false;
            default:
                return false;
        }
    }
    
    public List<StatusReserva> getTransicoesValidas() {
        return Arrays.stream(values())
            .filter(this::podeTransitarPara)
            .collect(Collectors.toList());
    }
    
    public boolean isAtiva() {
        return this == ATIVA;
    }
    
    public boolean isCancelada() {
        return this == CANCELADA;
    }
    
    public boolean isConcluida() {
        return this == CONCLUIDA;
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s", name(), descricao);
    }
}
