package model;

import java.time.LocalDate;
import java.util.Objects;

import enums.StatusEmprestimo;
import util.DateUtils;

public class Emprestimo extends OperacaoBiblioteca {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private LocalDate dataLimite;
    private StatusEmprestimo status;
    
    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo, LocalDate dataLimite) {
        super(usuario, livro, dataEmprestimo);
        
        if (dataLimite == null) {
            throw new IllegalArgumentException("Data limite não pode ser nula");
        }
        
        if (dataLimite.isBefore(dataEmprestimo) || dataLimite.isEqual(dataEmprestimo)) {
            throw new IllegalArgumentException("Data limite deve ser posterior à data de empréstimo");
        }
        
        this.dataEmprestimo = dataEmprestimo;
        this.dataLimite = dataLimite;
        this.status = StatusEmprestimo.ATIVO;
        this.dataDevolucao = null;
    }
    
    public Emprestimo(Usuario usuario, Livro livro, String dataEmprestimo, String dataLimite) {
        this(usuario, livro, DateUtils.parseDate(dataEmprestimo), DateUtils.parseDate(dataLimite));
    }
    
    @Override
    public String getTipoOperacao() {
        return "Empréstimo";
    }
    
    @Override
    public String getStatusOperacao() {
        return status.getDescricao();
    }
    
    @Override
    public boolean podeFinalizar() {
        return status == StatusEmprestimo.ATIVO || status == StatusEmprestimo.ATRASADO;
    }
    
    @Override
    public void finalizarOperacao() {
        devolverLivro(LocalDate.now());
    }
    
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public String getDataEmprestimoFormatada() {
        return DateUtils.formatDate(dataEmprestimo);
    }
    
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        if (dataEmprestimo == null) {
            throw new IllegalArgumentException("Data de empréstimo não pode ser nula");
        }
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public void setDataEmprestimo(String dataEmprestimo) {
        setDataEmprestimo(DateUtils.parseDate(dataEmprestimo));
    }
    
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    
    public String getDataDevolucaoFormatada() {
        return DateUtils.formatDate(dataDevolucao);
    }
    
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    
    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao != null ? DateUtils.parseDate(dataDevolucao) : null;
    }
    
    public LocalDate getDataLimite() {
        return dataLimite;
    }
    
    public String getDataLimiteFormatada() {
        return DateUtils.formatDate(dataLimite);
    }
    
    public void setDataLimite(LocalDate dataLimite) {
        if (dataLimite == null) {
            throw new IllegalArgumentException("Data limite não pode ser nula");
        }
        if (dataLimite.isBefore(dataEmprestimo) || dataLimite.isEqual(dataEmprestimo)) {
            throw new IllegalArgumentException("Data limite deve ser posterior à data de empréstimo");
        }
        this.dataLimite = dataLimite;
    }
    
    public void setDataLimite(String dataLimite) {
        setDataLimite(DateUtils.parseDate(dataLimite));
    }
    
    public StatusEmprestimo getStatus() {
        return status;
    }
    
    public void setStatus(StatusEmprestimo status) {
        if (status == null) {
            throw new IllegalArgumentException("Status não pode ser nulo");
        }
        if (!this.status.podeTransitarPara(status)) {
            throw new IllegalStateException(
                String.format("Não é possível transitar de %s para %s", this.status, status)
            );
        }
        this.status = status;
    }
    
    public void devolverLivro(LocalDate dataDevolucao) {
        if (this.status == StatusEmprestimo.DEVOLVIDO) {
            throw new IllegalStateException("Empréstimo já foi devolvido");
        }
        if (dataDevolucao == null) {
            dataDevolucao = LocalDate.now();
        }
        this.dataDevolucao = dataDevolucao;
        this.status = StatusEmprestimo.DEVOLVIDO;
        getLivro().devolver();
    }
    
    public void devolverLivro(String dataDevolucao) {
        devolverLivro(dataDevolucao != null ? DateUtils.parseDate(dataDevolucao) : LocalDate.now());
    }
    
    public boolean isAtrasado() {
        if (status != StatusEmprestimo.ATIVO) {
            return false;
        }
        LocalDate hoje = LocalDate.now();
        return hoje.isAfter(dataLimite);
    }
    
    public void atualizarStatus() {
        if (status == StatusEmprestimo.ATIVO && isAtrasado()) {
            try {
                setStatus(StatusEmprestimo.ATRASADO);
            } catch (IllegalStateException e) {
            }
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Emprestimo emprestimo = (Emprestimo) obj;
        return Objects.equals(getUsuario(), emprestimo.getUsuario()) &&
               Objects.equals(getLivro(), emprestimo.getLivro()) &&
               Objects.equals(dataEmprestimo, emprestimo.dataEmprestimo);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getUsuario(), getLivro(), dataEmprestimo);
    }
    
    @Override
    public String toString() {
        String devolucao = dataDevolucao != null ? getDataDevolucaoFormatada() : "Não devolvido";
        return String.format("Empréstimo: %s emprestou '%s' em %s (Limite: %s, Devolução: %s) - %s",
                           getUsuario().getNome(), getLivro().getTitulo(), getDataEmprestimoFormatada(), 
                           getDataLimiteFormatada(), devolucao, status.getDescricao());
    }
}
