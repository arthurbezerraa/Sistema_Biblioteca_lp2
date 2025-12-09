package model;

import enums.TemaInterface;

public class PreferenciasUsuario {
    private String nomeExibicao;
    private TemaInterface tema;

    public PreferenciasUsuario() {
        this.nomeExibicao = "Usuário";
        this.tema = TemaInterface.CLARO;
    }

    public PreferenciasUsuario(String nomeExibicao, TemaInterface tema) {
        if (nomeExibicao == null || nomeExibicao.trim().isEmpty()) {
            this.nomeExibicao = "Usuário";
        } else {
            this.nomeExibicao = nomeExibicao.trim();
        }

        this.tema = tema != null ? tema : TemaInterface.CLARO;
    }

    public String getNomeExibicao() {
        return nomeExibicao;
    }

    public void setNomeExibicao(String nomeExibicao) {
        if (nomeExibicao == null || nomeExibicao.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome de exibição não pode ser vazio");
        }
        this.nomeExibicao = nomeExibicao.trim();
    }

    public TemaInterface getTema() {
        return tema;
    }

    public void setTema(TemaInterface tema) {
        if (tema == null) {
            throw new IllegalArgumentException("Tema não pode ser nulo");
        }
        this.tema = tema;
    }

    public void alternarTema() {
        this.tema = tema.alternar();
    }

    @Override
    public String toString() {
        return String.format("Nome de Exibição: %s | Tema: %s", nomeExibicao, tema.getNome());
    }
}