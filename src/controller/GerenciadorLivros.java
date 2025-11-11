package controller;

import exception.LivroJaExisteException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Autor;
import model.Categoria;
import model.Livro;

public class GerenciadorLivros {
    private List<Livro> livros;

    public GerenciadorLivros() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) throws LivroJaExisteException {
        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo");
        }

        if (buscarPorIsbn(livro.getIsbn()) != null) {
            throw new LivroJaExisteException("Livro com ISBN " + livro.getIsbn() + " já existe");
        }

        livros.add(livro);
    }

    public Livro buscarPorIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }

        return livros.stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return livros.stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Livro> buscarPorAutor(Autor autor) {
        if (autor == null) {
            return new ArrayList<>();
        }

        return livros.stream()
                .filter(l -> l.getAutor().equals(autor))
                .collect(Collectors.toList());
    }

    public List<Livro> buscarPorCategoria(Categoria categoria) {
        if (categoria == null) {
            return new ArrayList<>();
        }

        return livros.stream()
                .filter(l -> l.getCategoria().equals(categoria))
                .collect(Collectors.toList());
    }

    public List<Livro> listarLivros() {
        return new ArrayList<>(livros);
    }

    public List<Livro> listarLivrosDisponiveis() {
        return livros.stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
    }

    public List<Livro> listarLivrosIndisponiveis() {
        return livros.stream()
                .filter(l -> !l.isDisponivel())
                .collect(Collectors.toList());
    }

    public void atualizarLivro(Livro livroAtualizado) {
        if (livroAtualizado == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo");
        }

        Livro livro = buscarPorIsbn(livroAtualizado.getIsbn());
        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado");
        }

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setCategoria(livroAtualizado.getCategoria());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());
    }

    public void removerLivro(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN não pode ser vazio");
        }

        Livro livro = buscarPorIsbn(isbn);
        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado");
        }

        livros.remove(livro);
    }

    public boolean temLivros() {
        return !livros.isEmpty();
    }

    public int getQuantidadeLivros() {
        return livros.size();
    }

    public int getQuantidadeLivrosDisponiveis() {
        return (int) livros.stream()
                .filter(Livro::isDisponivel)
                .count();
    }

    public int getQuantidadeLivrosIndisponiveis() {
        return (int) livros.stream()
                .filter(l -> !l.isDisponivel())
                .count();
    }
}
