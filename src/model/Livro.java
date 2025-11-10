package model;

import java.util.Objects;
import model.Autor;
import model.Categoria;

public class Livro {
    private final String isbn;
    private String titulo;
    private Autor autor;
    private Categoria categoria;
    private boolean disponivel;
    private int anoPublicacao;
    
    public Livro(String isbn, String titulo, Autor autor, Categoria categoria, int anoPublicacao) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN não pode ser vazio");
        }
        
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        
        if (autor == null) {
            throw new IllegalArgumentException("Autor não pode ser nulo");
        }
        
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
        
        int anoAtual = java.time.LocalDate.now().getYear();
        if (anoPublicacao < 1000 || anoPublicacao > anoAtual + 10) {
            throw new IllegalArgumentException(
                String.format("Ano de publicação inválido. Deve estar entre 1000 e %d", anoAtual + 10)
            );
        }
        
        this.isbn = isbn.trim();
        this.titulo = titulo.trim();
        this.autor = autor;
        this.categoria = categoria;
        this.disponivel = true;
        this.anoPublicacao = anoPublicacao;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título não pode ser vazio");
        }
        this.titulo = titulo;
    }
    
    public Autor getAutor() {
        return autor;
    }
    
    public void setAutor(Autor autor) {
        if (autor == null) {
            throw new IllegalArgumentException("Autor não pode ser nulo");
        }
        this.autor = autor;
    }
    
    public Categoria getCategoria() {
        return categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
        this.categoria = categoria;
    }
    
    public boolean isDisponivel() {
        return disponivel;
    }
    
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    
    public void setAnoPublicacao(int anoPublicacao) {
        int anoAtual = java.time.LocalDate.now().getYear();
        if (anoPublicacao < 1000 || anoPublicacao > anoAtual + 10) {
            throw new IllegalArgumentException(
                String.format("Ano de publicação inválido. Deve estar entre 1000 e %d", anoAtual + 10)
            );
        }
        this.anoPublicacao = anoPublicacao;
    }
    
    public void emprestar() {
        if (!disponivel) {
            throw new IllegalStateException("Livro não está disponível para empréstimo");
        }
        this.disponivel = false;
    }
    
    public void devolver() {
        this.disponivel = true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Livro livro = (Livro) obj;
        return Objects.equals(isbn, livro.isbn);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
    
    @Override
    public String toString() {
        String status = disponivel ? "Disponível" : "Indisponível";
        return String.format("Livro: %s - %s (%s) - %s - %s", 
                           titulo, autor.getNome(), anoPublicacao, categoria.getNome(), status);
    }
}
