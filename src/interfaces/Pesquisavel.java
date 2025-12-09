package interfaces;

import java.util.List;

public interface Pesquisavel<T> {

    List<T> buscar(String termo);

    List<T> listarTodos();

    boolean existe(String criterio);

    int contarTotal();
}