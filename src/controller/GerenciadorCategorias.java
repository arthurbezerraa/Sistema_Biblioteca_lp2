package controller;

import model.Categoria;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorCategorias {
    private List<Categoria> categorias;

    public GerenciadorCategorias() {
        this.categorias = new ArrayList<>();
    }

    public void adicionarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
        categorias.add(categoria);
    }

    public Categoria buscarPorNome(String nome) {
        return categorias.stream()
                .filter(c -> c.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Categoria> listarCategorias() {
        return new ArrayList<>(categorias);
    }

    public void atualizarCategoria(Categoria categoriaAtualizada) {
        Categoria categoria = buscarPorNome(categoriaAtualizada.getNome());
        if (categoria != null) {
            categoria.setDescricao(categoriaAtualizada.getDescricao());
        }
    }

    public void removerCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }

    public int getQuantidadeCategorias() {
        return categorias.size();
    }
}