package controller;

import model.Editora;
import exception.EditoraJaExisteException;
import exception.EditoraNaoEncontradaException;
import interfaces.Pesquisavel;
import interfaces.Relatorivel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorEditoras implements Pesquisavel<Editora>, Relatorivel {
    private List<Editora> editoras;

    public GerenciadorEditoras() {
        this.editoras = new ArrayList<>();
    }

    public void adicionarEditora(Editora editora) throws EditoraJaExisteException {
        if (editora == null) {
            throw new IllegalArgumentException("Editora não pode ser nula");
        }

        if (existe(editora.getCnpj())) {
            throw new EditoraJaExisteException("Editora com CNPJ " + editora.getCnpjFormatado() + " já existe");
        }

        editoras.add(editora);
    }

    public Editora buscarPorCnpj(String cnpj) throws EditoraNaoEncontradaException {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("CNPJ não pode ser vazio");
        }

        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");

        return editoras.stream()
                .filter(e -> e.getCnpj().equals(cnpjLimpo))
                .findFirst()
                .orElseThrow(() -> new EditoraNaoEncontradaException("Editora com CNPJ " + cnpj + " não encontrada"));
    }

    public List<Editora> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String nomeBusca = nome.trim().toLowerCase();

        return editoras.stream()
                .filter(e -> e.getNome().toLowerCase().contains(nomeBusca))
                .collect(Collectors.toList());
    }

    public List<Editora> listarEditoras() {
        return new ArrayList<>(editoras);
    }

    public void atualizarEditora(String cnpj, String novoNome, String novoEndereco, String novoTelefone)
            throws EditoraNaoEncontradaException {
        Editora editora = buscarPorCnpj(cnpj);

        if (novoNome != null && !novoNome.trim().isEmpty()) {
            editora.setNome(novoNome);
        }

        if (novoEndereco != null && !novoEndereco.trim().isEmpty()) {
            editora.setEndereco(novoEndereco);
        }

        if (novoTelefone != null && !novoTelefone.trim().isEmpty()) {
            editora.setTelefone(novoTelefone);
        }
    }

    public void removerEditora(String cnpj) throws EditoraNaoEncontradaException {
        Editora editora = buscarPorCnpj(cnpj);
        editoras.remove(editora);
    }

    @Override
    public List<Editora> buscar(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String termoBusca = termo.trim().toLowerCase();

        return editoras.stream()
                .filter(e -> e.getNome().toLowerCase().contains(termoBusca) ||
                        e.getCnpj().contains(termoBusca) ||
                        e.getEndereco().toLowerCase().contains(termoBusca))
                .collect(Collectors.toList());
    }

    @Override
    public List<Editora> listarTodos() {
        return listarEditoras();
    }

    @Override
    public boolean existe(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return false;
        }

        String criterioLimpo = criterio.replaceAll("[^0-9]", "");

        return editoras.stream()
                .anyMatch(e -> e.getCnpj().equals(criterioLimpo));
    }

    @Override
    public int contarTotal() {
        return editoras.size();
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== RELATÓRIO DE EDITORAS ==========\n\n");

        if (editoras.isEmpty()) {
            sb.append("Nenhuma editora cadastrada.\n");
        } else {
            for (Editora editora : editoras) {
                sb.append("Nome: ").append(editora.getNome()).append("\n");
                sb.append("CNPJ: ").append(editora.getCnpjFormatado()).append("\n");
                sb.append("Endereço: ").append(editora.getEndereco()).append("\n");
                sb.append("Telefone: ").append(editora.getTelefone()).append("\n");
                sb.append("-------------------------------------------\n");
            }
        }

        sb.append("\nTotal de editoras: ").append(editoras.size()).append("\n");

        return sb.toString();
    }

    @Override
    public String gerarEstatisticas() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== ESTATÍSTICAS DE EDITORAS ==========\n\n");
        sb.append("Total de editoras cadastradas: ").append(editoras.size()).append("\n");

        return sb.toString();
    }

    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("EXPORTAÇÃO DE EDITORAS\n");
        sb.append("======================\n\n");

        for (Editora editora : editoras) {
            sb.append(editora.getNome()).append(";");
            sb.append(editora.getCnpjFormatado()).append(";");
            sb.append(editora.getEndereco()).append(";");
            sb.append(editora.getTelefone()).append("\n");
        }

        return sb.toString();
    }
}
