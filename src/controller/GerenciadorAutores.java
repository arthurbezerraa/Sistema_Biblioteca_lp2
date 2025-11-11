package controller;

import java.util.ArrayList;
import java.util.List;
import model.Autor;

public class GerenciadorAutores {
    private List<Autor> autores;

    public GerenciadorAutores() {
        this.autores = new ArrayList<>();
    }

    public void adicionarAutor(Autor autor) {
        if (autor == null) {
            throw new IllegalArgumentException("Autor não pode ser nulo");
        }
        autores.add(autor);
    }

    public Autor buscarPorNome(String nome) {
        return autores.stream()
                .filter(a -> a.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Autor> listarAutores() {
        return new ArrayList<>(autores);
    }

    public void atualizarAutor(Autor autorAtualizado) {
        Autor autor = buscarPorNome(autorAtualizado.getNome());
        if (autor != null) {
            autor.setNacionalidade(autorAtualizado.getNacionalidade());
            autor.setDataNascimento(autorAtualizado.getDataNascimento());
        }
    }

    public void removerAutor(Autor autor) {
        autores.remove(autor);
    }

    public int getQuantidadeAutores() {
        return autores.size();
    }
}