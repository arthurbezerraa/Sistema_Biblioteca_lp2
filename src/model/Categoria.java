package model;

import java.util.Objects;

public class Categoria {
    private String nome;
    private String descricao;
    
    public Categoria(String nome, String descricao) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
        }
        
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da categoria não pode ser vazia");
        }
        
        this.nome = nome.trim();
        this.descricao = descricao.trim();
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da categoria não pode ser vazio");
        }
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição da categoria não pode ser vazia");
        }
        this.descricao = descricao;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Categoria categoria = (Categoria) obj;
        return Objects.equals(nome, categoria.nome);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
    
    @Override
    public String toString() {
        return String.format("Categoria: %s - %s", nome, descricao);
    }
}
