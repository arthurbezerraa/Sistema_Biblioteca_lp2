package model;

import java.time.LocalDate;
import java.util.Objects;

public class Avaliacao {
    private Usuario usuario;
    private Livro livro;
    private int nota;
    private String comentario;
    private LocalDate dataAvaliacao;

    public Avaliacao(Usuario usuario, Livro livro, int nota, String comentario, LocalDate dataAvaliacao) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        if (livro == null) {
            throw new IllegalArgumentException("Livro não pode ser nulo");
        }

        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5");
        }

        if (comentario == null || comentario.trim().isEmpty()) {
            throw new IllegalArgumentException("Comentário não pode ser vazio");
        }

        if (comentario.trim().length() < 10) {
            throw new IllegalArgumentException("Comentário deve ter pelo menos 10 caracteres");
        }

        if (dataAvaliacao == null) {
            throw new IllegalArgumentException("Data da avaliação não pode ser nula");
        }

        this.usuario = usuario;
        this.livro = livro;
        this.nota = nota;
        this.comentario = comentario.trim();
        this.dataAvaliacao = dataAvaliacao;
    }

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

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("Nota deve estar entre 1 e 5");
        }
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        if (comentario == null || comentario.trim().isEmpty()) {
            throw new IllegalArgumentException("Comentário não pode ser vazio");
        }

        if (comentario.trim().length() < 10) {
            throw new IllegalArgumentException("Comentário deve ter pelo menos 10 caracteres");
        }

        this.comentario = comentario.trim();
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        if (dataAvaliacao == null) {
            throw new IllegalArgumentException("Data da avaliação não pode ser nula");
        }
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getDataAvaliacaoFormatada() {
        return util.DateUtils.formatDate(dataAvaliacao);
    }

    public String getNotaEstrelas() {
        return "★".repeat(nota) + "☆".repeat(5 - nota);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Avaliacao avaliacao = (Avaliacao) obj;
        return Objects.equals(usuario, avaliacao.usuario) &&
                Objects.equals(livro, avaliacao.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, livro);
    }

    @Override
    public String toString() {
        return String.format("%s avaliou '%s' com %s (%d/5) em %s",
                usuario.getNome(),
                livro.getTitulo(),
                getNotaEstrelas(),
                nota,
                getDataAvaliacaoFormatada()
        );
    }
}