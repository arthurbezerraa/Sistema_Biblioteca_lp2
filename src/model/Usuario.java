package model;

import java.util.Objects;
import util.CpfUtils;
import util.EmailUtils;

public abstract class Usuario {
    protected String nome;
    protected String email;
    protected final String cpf;
    
    public Usuario(String nome, String email, String cpf) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        
        if (!EmailUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Email inválido");
        }
        
        if (!CpfUtils.isValidCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }
        
        this.nome = nome.trim();
        this.email = email.trim();
        this.cpf = cpf;
    }
    
    public abstract String getTipoUsuario();
    
    public abstract String getInformacoesEspecificas();
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        if (!EmailUtils.isValidEmail(email)) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email.trim();
    }
    
    public String getCpf() {
        return cpf;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return Objects.equals(cpf, usuario.cpf);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%s)", nome, email, cpf);
    }
}
