package controller;

import model.PreferenciasUsuario;
import enums.TemaInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GerenciadorPreferencias {
    private static final String ARQUIVO_PREFERENCIAS = "preferencias_usuario.txt";
    private PreferenciasUsuario preferencias;

    public GerenciadorPreferencias() {
        this.preferencias = new PreferenciasUsuario();
        carregarPreferencias();
    }

    public void carregarPreferencias() {
        try {
            if (!Files.exists(Paths.get(ARQUIVO_PREFERENCIAS))) {

                salvarPreferencias();
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_PREFERENCIAS));

            String nomeExibicao = null;
            TemaInterface tema = TemaInterface.CLARO;

            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();

                if (linha.isEmpty() || linha.startsWith("#")) {
                    continue;
                }

                String[] partes = linha.split("=", 2);

                if (partes.length == 2) {
                    String chave = partes[0].trim();
                    String valor = partes[1].trim();

                    if (chave.equals("nomeExibicao")) {
                        nomeExibicao = valor;
                    } else if (chave.equals("tema")) {
                        tema = TemaInterface.fromString(valor);
                    }
                }
            }

            reader.close();

            if (nomeExibicao != null && !nomeExibicao.isEmpty()) {
                preferencias = new PreferenciasUsuario(nomeExibicao, tema);
            }

        } catch (IOException e) {
            System.err.println("Erro ao carregar preferências: " + e.getMessage());

        }
    }

    public void salvarPreferencias() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_PREFERENCIAS));

            writer.write("# Preferências do Sistema de Biblioteca\n");
            writer.write("# Este arquivo armazena as configurações do usuário\n");
            writer.write("# Gerado automaticamente - NÃO edite manualmente\n");
            writer.write("\n");
            writer.write("# Nome de exibição do usuário\n");
            writer.write("nomeExibicao=" + preferencias.getNomeExibicao() + "\n");
            writer.write("\n");
            writer.write("# Tema da interface (CLARO ou ESCURO)\n");
            writer.write("tema=" + preferencias.getTema().name() + "\n");

            writer.close();

        } catch (IOException e) {
            System.err.println("Erro ao salvar preferências: " + e.getMessage());
        }
    }

    public PreferenciasUsuario getPreferencias() {
        return preferencias;
    }

    public void atualizarNomeExibicao(String novoNome) {
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome de exibição não pode ser vazio");
        }

        preferencias.setNomeExibicao(novoNome);
        salvarPreferencias();
    }

    public void atualizarTema(TemaInterface novoTema) {
        if (novoTema == null) {
            throw new IllegalArgumentException("Tema não pode ser nulo");
        }

        preferencias.setTema(novoTema);
        salvarPreferencias();
    }

    public void alternarTema() {
        preferencias.alternarTema();
        salvarPreferencias();
    }

    public void resetarPreferencias() {
        preferencias = new PreferenciasUsuario();
        salvarPreferencias();
    }

    public String exibirPreferencias() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n========== PREFERÊNCIAS DO SISTEMA ==========\n\n");
        sb.append("Nome de Exibição: ").append(preferencias.getNomeExibicao()).append("\n");
        sb.append("Tema: ").append(preferencias.getTema().getNome()).append("\n");
        sb.append("\n=============================================\n");

        return sb.toString();
    }
}