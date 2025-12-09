package controller;

import model.Avaliacao;
import model.Usuario;
import model.Livro;
import exception.AvaliacaoInvalidaException;
import interfaces.Pesquisavel;
import interfaces.Relatorivel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorAvaliacoes implements Pesquisavel<Avaliacao>, Relatorivel {
    private List<Avaliacao> avaliacoes;

    public GerenciadorAvaliacoes() {
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) throws AvaliacaoInvalidaException {
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não pode ser nula");
        }

        boolean jaAvaliou = avaliacoes.stream()
                .anyMatch(a -> a.getUsuario().equals(avaliacao.getUsuario()) &&
                        a.getLivro().equals(avaliacao.getLivro()));

        if (jaAvaliou) {
            throw new AvaliacaoInvalidaException(
                    "Usuário " + avaliacao.getUsuario().getNome() +
                            " já avaliou o livro '" + avaliacao.getLivro().getTitulo() + "'"
            );
        }

        avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> buscarPorUsuario(Usuario usuario) {
        if (usuario == null) {
            return new ArrayList<>();
        }

        return avaliacoes.stream()
                .filter(a -> a.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    public List<Avaliacao> buscarPorLivro(Livro livro) {
        if (livro == null) {
            return new ArrayList<>();
        }

        return avaliacoes.stream()
                .filter(a -> a.getLivro().equals(livro))
                .collect(Collectors.toList());
    }

    public List<Avaliacao> buscarPorNota(int nota) {
        if (nota < 1 || nota > 5) {
            return new ArrayList<>();
        }

        return avaliacoes.stream()
                .filter(a -> a.getNota() == nota)
                .collect(Collectors.toList());
    }

    public List<Avaliacao> listarAvaliacoes() {
        return new ArrayList<>(avaliacoes);
    }

    public void atualizarAvaliacao(Usuario usuario, Livro livro, int novaNota, String novoComentario)
            throws AvaliacaoInvalidaException {
        Avaliacao avaliacao = avaliacoes.stream()
                .filter(a -> a.getUsuario().equals(usuario) && a.getLivro().equals(livro))
                .findFirst()
                .orElseThrow(() -> new AvaliacaoInvalidaException(
                        "Avaliação não encontrada para o usuário " + usuario.getNome() +
                                " e livro '" + livro.getTitulo() + "'"
                ));

        if (novaNota >= 1 && novaNota <= 5) {
            avaliacao.setNota(novaNota);
        }

        if (novoComentario != null && !novoComentario.trim().isEmpty()) {
            avaliacao.setComentario(novoComentario);
        }
    }

    public void removerAvaliacao(Usuario usuario, Livro livro) throws AvaliacaoInvalidaException {
        Avaliacao avaliacao = avaliacoes.stream()
                .filter(a -> a.getUsuario().equals(usuario) && a.getLivro().equals(livro))
                .findFirst()
                .orElseThrow(() -> new AvaliacaoInvalidaException(
                        "Avaliação não encontrada para o usuário " + usuario.getNome() +
                                " e livro '" + livro.getTitulo() + "'"
                ));

        avaliacoes.remove(avaliacao);
    }

    public double calcularMediaLivro(Livro livro) {
        List<Avaliacao> avaliacoesLivro = buscarPorLivro(livro);

        if (avaliacoesLivro.isEmpty()) {
            return 0.0;
        }

        return avaliacoesLivro.stream()
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Avaliacao> buscar(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String termoBusca = termo.trim().toLowerCase();

        return avaliacoes.stream()
                .filter(a -> a.getUsuario().getNome().toLowerCase().contains(termoBusca) ||
                        a.getLivro().getTitulo().toLowerCase().contains(termoBusca) ||
                        a.getComentario().toLowerCase().contains(termoBusca))
                .collect(Collectors.toList());
    }

    @Override
    public List<Avaliacao> listarTodos() {
        return listarAvaliacoes();
    }

    @Override
    public boolean existe(String criterio) {
        if (criterio == null || criterio.trim().isEmpty()) {
            return false;
        }

        String criterioBusca = criterio.trim().toLowerCase();

        return avaliacoes.stream()
                .anyMatch(a -> a.getUsuario().getCpf().equals(criterio) ||
                        a.getLivro().getIsbn().equals(criterio));
    }

    @Override
    public int contarTotal() {
        return avaliacoes.size();
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== RELATÓRIO DE AVALIAÇÕES ==========\n\n");

        if (avaliacoes.isEmpty()) {
            sb.append("Nenhuma avaliação cadastrada.\n");
        } else {
            for (Avaliacao avaliacao : avaliacoes) {
                sb.append("Usuário: ").append(avaliacao.getUsuario().getNome()).append("\n");
                sb.append("Livro: ").append(avaliacao.getLivro().getTitulo()).append("\n");
                sb.append("Nota: ").append(avaliacao.getNotaEstrelas()).append(" (").append(avaliacao.getNota()).append("/5)\n");
                sb.append("Comentário: ").append(avaliacao.getComentario()).append("\n");
                sb.append("Data: ").append(avaliacao.getDataAvaliacaoFormatada()).append("\n");
                sb.append("-------------------------------------------\n");
            }
        }

        sb.append("\nTotal de avaliações: ").append(avaliacoes.size()).append("\n");

        return sb.toString();
    }

    @Override
    public String gerarEstatisticas() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== ESTATÍSTICAS DE AVALIAÇÕES ==========\n\n");
        sb.append("Total de avaliações: ").append(avaliacoes.size()).append("\n");

        if (!avaliacoes.isEmpty()) {
            double mediaGeral = avaliacoes.stream()
                    .mapToInt(Avaliacao::getNota)
                    .average()
                    .orElse(0.0);

            sb.append("Média geral das avaliações: ").append(String.format("%.2f", mediaGeral)).append("\n");

            for (int nota = 5; nota >= 1; nota--) {
                final int notaFinal = nota;
                long count = avaliacoes.stream()
                        .filter(a -> a.getNota() == notaFinal)
                        .count();
                sb.append("Avaliações com ").append(notaFinal).append(" estrelas: ").append(count).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public String exportarDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("EXPORTAÇÃO DE AVALIAÇÕES\n");
        sb.append("========================\n\n");

        for (Avaliacao avaliacao : avaliacoes) {
            sb.append(avaliacao.getUsuario().getNome()).append(";");
            sb.append(avaliacao.getLivro().getTitulo()).append(";");
            sb.append(avaliacao.getNota()).append(";");
            sb.append(avaliacao.getComentario()).append(";");
            sb.append(avaliacao.getDataAvaliacaoFormatada()).append("\n");
        }

        return sb.toString();
    }
}