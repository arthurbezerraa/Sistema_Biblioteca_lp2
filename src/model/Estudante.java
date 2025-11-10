package model;

public class Estudante extends Usuario {
    private String matricula;
    private String curso;
    
    public Estudante(String nome, String email, String cpf, String matricula, String curso) {
        super(nome, email, cpf);
        
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula não pode ser vazia");
        }
        
        if (curso == null || curso.trim().isEmpty()) {
            throw new IllegalArgumentException("Curso não pode ser vazio");
        }
        
        this.matricula = matricula.trim();
        this.curso = curso.trim();
    }
    
    @Override
    public String getTipoUsuario() {
        return "Estudante";
    }
    
    @Override
    public String getInformacoesEspecificas() {
        return String.format("Matrícula: %s, Curso: %s", matricula, curso);
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        if (matricula == null || matricula.trim().isEmpty()) {
            throw new IllegalArgumentException("Matrícula não pode ser vazia");
        }
        this.matricula = matricula;
    }
    
    public String getCurso() {
        return curso;
    }
    
    public void setCurso(String curso) {
        if (curso == null || curso.trim().isEmpty()) {
            throw new IllegalArgumentException("Curso não pode ser vazio");
        }
        this.curso = curso;
    }
    
    @Override
    public String toString() {
        return String.format("Estudante: %s - %s (%s) - %s", 
                           nome, email, cpf, getInformacoesEspecificas());
    }
}
