package model;

public class Professor extends Usuario {
    private String departamento;
    private String titulacao;
    
    public Professor(String nome, String email, String cpf, String departamento, String titulacao) {
        super(nome, email, cpf);
        
        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Departamento não pode ser vazio");
        }
        
        if (titulacao == null || titulacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Titulação não pode ser vazia");
        }
        
        this.departamento = departamento.trim();
        this.titulacao = titulacao.trim();
    }
    
    @Override
    public String getTipoUsuario() {
        return "Professor";
    }
    
    @Override
    public String getInformacoesEspecificas() {
        return String.format("Departamento: %s, Titulação: %s", departamento, titulacao);
    }
    
    public String getDepartamento() {
        return departamento;
    }
    
    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("Departamento não pode ser vazio");
        }
        this.departamento = departamento;
    }
    
    public String getTitulacao() {
        return titulacao;
    }
    
    public void setTitulacao(String titulacao) {
        if (titulacao == null || titulacao.trim().isEmpty()) {
            throw new IllegalArgumentException("Titulação não pode ser vazia");
        }
        this.titulacao = titulacao;
    }
    
    @Override
    public String toString() {
        return String.format("Professor: %s - %s (%s) - %s", 
                           nome, email, cpf, getInformacoesEspecificas());
    }
}
