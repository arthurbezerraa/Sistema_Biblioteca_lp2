package model;

import java.time.LocalDate;

public abstract class OperacaoBiblioteca {
    protected Usuario usuario;
    protected Livro livro;
    protected LocalDate dataOperacao;
    
    public OperacaoBiblioteca(Usuario usuario, Livro livro, LocalDate dataOperacao) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo");
        }
        
        if (dataOperacao == null) {
            throw new IllegalArgumentException("Data da operação não pode ser nula");
        }
        
        this.usuario = usuario;
        this.livro = livro;
        this.dataOperacao = dataOperacao;
    }
    
    public abstract String getTipoOperacao();
    
    public abstract String getStatusOperacao();
    
    public abstract boolean podeFinalizar();
    
    public abstract void finalizarOperacao();
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        this.usuario = usuario;
    }
    
    public Livro getLivro() {
        return livro;
    }
    
    public void setLivro(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo");
        }
        this.livro = livro;
    }
    
    public LocalDate getDataOperacao() {
        return dataOperacao;
    }
    
    public void setDataOperacao(LocalDate dataOperacao) {
        if (dataOperacao == null) {
            throw new IllegalArgumentException("Data da operação não pode ser nula");
        }
        this.dataOperacao = dataOperacao;
    }
    
    public String getDataOperacaoFormatada() {
        return util.DateUtils.formatDate(dataOperacao);
    }
}

