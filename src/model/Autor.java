package model;

import java.time.LocalDate;
import java.util.Objects;
import util.DateUtils;

public class Autor {
    private String nome;
    private String nacionalidade;
    private LocalDate dataNascimento;
    
    public Autor(String nome, String nacionalidade, LocalDate dataNascimento) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do autor não pode ser vazio");
        }
        
        if (nacionalidade == null || nacionalidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Nacionalidade não pode ser vazia");
        }
        
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }
        
        LocalDate hoje = LocalDate.now();
        if (dataNascimento.isAfter(hoje.minusYears(10))) {
            throw new IllegalArgumentException("Data de nascimento inválida - autor muito jovem");
        }
        if (dataNascimento.isBefore(hoje.minusYears(150))) {
            throw new IllegalArgumentException("Data de nascimento inválida - autor muito velho");
        }
        
        this.nome = nome.trim();
        this.nacionalidade = nacionalidade.trim();
        this.dataNascimento = dataNascimento;
    }
    
    public Autor(String nome, String nacionalidade, String dataNascimento) {
        this(nome, nacionalidade, DateUtils.parseDate(dataNascimento));
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do autor não pode ser vazio");
        }
        this.nome = nome;
    }
    
    public String getNacionalidade() {
        return nacionalidade;
    }
    
    public void setNacionalidade(String nacionalidade) {
        if (nacionalidade == null || nacionalidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Nacionalidade não pode ser vazia");
        }
        this.nacionalidade = nacionalidade;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public String getDataNascimentoFormatada() {
        return DateUtils.formatDate(dataNascimento);
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null) {
            throw new IllegalArgumentException("Data de nascimento não pode ser nula");
        }
        
        LocalDate hoje = LocalDate.now();
        if (dataNascimento.isAfter(hoje.minusYears(10))) {
            throw new IllegalArgumentException("Data de nascimento inválida - autor muito jovem");
        }
        if (dataNascimento.isBefore(hoje.minusYears(150))) {
            throw new IllegalArgumentException("Data de nascimento inválida - autor muito velho");
        }
        
        this.dataNascimento = dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento) {
        setDataNascimento(DateUtils.parseDate(dataNascimento));
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Autor autor = (Autor) obj;
        return Objects.equals(nome, autor.nome);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
    
    @Override
    public String toString() {
        return String.format("Autor: %s (%s) - Nascido em %s", nome, nacionalidade, getDataNascimentoFormatada());
    }
}
