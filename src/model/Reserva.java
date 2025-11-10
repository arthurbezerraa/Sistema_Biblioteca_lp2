package model;

import java.time.LocalDate;
import java.util.Objects;

import enums.StatusReserva;
import util.DateUtils;

public class Reserva extends OperacaoBiblioteca {
    private LocalDate dataReserva;
    private StatusReserva status;
    
    public Reserva(Usuario usuario, Livro livro, LocalDate dataReserva) {
        super(usuario, livro, dataReserva);
        this.dataReserva = dataReserva;
        this.status = StatusReserva.ATIVA;
    }
    
    public Reserva(Usuario usuario, Livro livro, String dataReserva) {
        this(usuario, livro, DateUtils.parseDate(dataReserva));
    }
    
    @Override
    public String getTipoOperacao() {
        return "Reserva";
    }
    
    @Override
    public String getStatusOperacao() {
        return status.getDescricao();
    }
    
    @Override
    public boolean podeFinalizar() {
        return status == StatusReserva.ATIVA;
    }
    
    @Override
    public void finalizarOperacao() {
        concluir();
    }
    
    public LocalDate getDataReserva() {
        return dataReserva;
    }
    
    public String getDataReservaFormatada() {
        return DateUtils.formatDate(dataReserva);
    }
    
    public void setDataReserva(LocalDate dataReserva) {
        if (dataReserva == null) {
            throw new IllegalArgumentException("Data de reserva não pode ser nula");
        }
        this.dataReserva = dataReserva;
    }
    
    public void setDataReserva(String dataReserva) {
        setDataReserva(DateUtils.parseDate(dataReserva));
    }
    
    public StatusReserva getStatus() {
        return status;
    }
    
    public void setStatus(StatusReserva status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        if (!this.status.podeTransitarPara(status)) {
            throw new IllegalStateException(
                String.format("Transição inválida: %s → %s", this.status, status)
            );
        }
        this.status = status;
    }
    
    public void cancelar() {
        setStatus(StatusReserva.CANCELADA);
    }
    
    public void concluir() {
        setStatus(StatusReserva.CONCLUIDA);
    }
    
    public boolean isAtiva() {
        return status == StatusReserva.ATIVA;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Reserva reserva = (Reserva) obj;
        return Objects.equals(getUsuario(), reserva.getUsuario()) &&
               Objects.equals(getLivro(), reserva.getLivro()) &&
               Objects.equals(dataReserva, reserva.dataReserva);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getUsuario(), getLivro(), dataReserva);
    }
    
    @Override
    public String toString() {
        return String.format("Reserva: %s reservou '%s' em %s - %s",
                           getUsuario().getNome(), getLivro().getTitulo(), getDataReservaFormatada(), status.getDescricao());
    }
}
