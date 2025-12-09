package model;

import java.util.Objects;

public class Editora {
    private String nome;
    private String cnpj;
    private String endereco;
    private String telefone;

    public Editora(String nome, String cnpj, String endereco, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da editora não pode ser vazio");
        }

        if (cnpj == null || !isValidCnpj(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido");
        }

        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser vazio");
        }

        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio");
        }

        this.nome = nome.trim();
        this.cnpj = cnpj.replaceAll("[^0-9]", "");
        this.endereco = endereco.trim();
        this.telefone = telefone.trim();
    }

    private boolean isValidCnpj(String cnpj) {
        if (cnpj == null) return false;

        String cnpjLimpo = cnpj.replaceAll("[^0-9]", "");

        if (cnpjLimpo.length() != 14) return false;

        if (cnpjLimpo.matches("(\\d)\\1{13}")) return false;

        return true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da editora não pode ser vazio");
        }
        this.nome = nome.trim();
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCnpjFormatado() {
        return String.format("%s.%s.%s/%s-%s",
                cnpj.substring(0, 2),
                cnpj.substring(2, 5),
                cnpj.substring(5, 8),
                cnpj.substring(8, 12),
                cnpj.substring(12, 14)
        );
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser vazio");
        }
        this.endereco = endereco.trim();
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio");
        }
        this.telefone = telefone.trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Editora editora = (Editora) obj;
        return Objects.equals(cnpj, editora.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj);
    }

    @Override
    public String toString() {
        return String.format("%s - CNPJ: %s", nome, getCnpjFormatado());
    }
}