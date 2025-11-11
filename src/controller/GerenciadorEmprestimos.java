package controller;

import enums.StatusEmprestimo;
import exception.EmprestimoInvalidoException;
import exception.LivroNaoDisponivelException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Emprestimo;
import model.Livro;
import model.Usuario;

public class GerenciadorEmprestimos {
    private List<Emprestimo> emprestimos;
    
    public GerenciadorEmprestimos() {
        this.emprestimos = new ArrayList<>();
    }
    
    public void realizarEmprestimo(Usuario usuario, Livro livro, String dataEmprestimo, String dataLimite) 
            throws LivroNaoDisponivelException, EmprestimoInvalidoException {
        if (usuario == null || livro == null) {
            throw new EmprestimoInvalidoException("Usuário e livro não podem ser nulos");
        }
        
        if (!livro.isDisponivel()) {
            throw new LivroNaoDisponivelException("Livro não está disponível para empréstimo");
        }
        
        boolean jaExisteEmprestimoAtivo = emprestimos.stream()
                .anyMatch(e -> e.getUsuario().equals(usuario) 
                        && e.getLivro().equals(livro) 
                        && (e.getStatus() == StatusEmprestimo.ATIVO || e.getStatus() == StatusEmprestimo.ATRASADO));
        
        if (jaExisteEmprestimoAtivo) {
            throw new EmprestimoInvalidoException("Usuário já possui um empréstimo ativo deste livro");
        }
        
        Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataLimite);
        emprestimos.add(emprestimo);
        livro.emprestar();
    }
    
    public List<Emprestimo> buscarPorUsuario(Usuario usuario) {
        return emprestimos.stream()
                .filter(e -> e.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }
    
    public List<Emprestimo> buscarPorLivro(Livro livro) {
        return emprestimos.stream()
                .filter(e -> e.getLivro().equals(livro))
                .collect(Collectors.toList());
    }
    
    public List<Emprestimo> listarEmprestimos() {
        emprestimos.forEach(Emprestimo::atualizarStatus);
        return new ArrayList<>(emprestimos);
    }
    
    public List<Emprestimo> listarEmprestimosAtivos() {
        emprestimos.forEach(Emprestimo::atualizarStatus);
        return emprestimos.stream()
                .filter(e -> e.getStatus() == StatusEmprestimo.ATIVO || e.getStatus() == StatusEmprestimo.ATRASADO)
                .collect(Collectors.toList());
    }
    
    public void devolverLivro(Emprestimo emprestimo, String dataDevolucao) {
        emprestimo.devolverLivro(dataDevolucao);
    }
    
    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }
    
    public int getQuantidadeEmprestimos() {
        return emprestimos.size();
    }
}
