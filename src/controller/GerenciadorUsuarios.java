package controller;

import exception.UsuarioJaExisteException;
import exception.UsuarioNaoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Estudante;
import model.Professor;
import model.Usuario;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios;

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public void adicionarUsuario(Usuario usuario) throws UsuarioJaExisteException {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        if (buscarPorCpf(usuario.getCpf()) != null) {
            throw new UsuarioJaExisteException("Usuário com CPF " + usuario.getCpf() + " já existe");
        }

        usuarios.add(usuario);
    }

    public Usuario buscarPorCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            return null;
        }

        return usuarios.stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> buscarPorNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return usuarios.stream()
                .filter(u -> u.getNome().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Estudante> listarEstudantes() {
        return usuarios.stream()
                .filter(u -> u instanceof Estudante)
                .map(u -> (Estudante) u)
                .collect(Collectors.toList());
    }

    public List<Professor> listarProfessores() {
        return usuarios.stream()
                .filter(u -> u instanceof Professor)
                .map(u -> (Professor) u)
                .collect(Collectors.toList());
    }

    public void atualizarUsuario(Usuario usuarioAtualizado) throws UsuarioNaoEncontradoException {
        if (usuarioAtualizado == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }

        Usuario usuario = buscarPorCpf(usuarioAtualizado.getCpf());
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());

        if (usuario instanceof Estudante && usuarioAtualizado instanceof Estudante) {
            Estudante estudante = (Estudante) usuario;
            Estudante estudanteAtualizado = (Estudante) usuarioAtualizado;
            estudante.setMatricula(estudanteAtualizado.getMatricula());
            estudante.setCurso(estudanteAtualizado.getCurso());
        } else if (usuario instanceof Professor && usuarioAtualizado instanceof Professor) {
            Professor professor = (Professor) usuario;
            Professor professorAtualizado = (Professor) usuarioAtualizado;
            professor.setDepartamento(professorAtualizado.getDepartamento());
            professor.setTitulacao(professorAtualizado.getTitulacao());
        }
    }

    public void removerUsuario(String cpf) throws UsuarioNaoEncontradoException {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser vazio");
        }

        Usuario usuario = buscarPorCpf(cpf);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Usuário não encontrado");
        }

        usuarios.remove(usuario);
    }

    public boolean temUsuarios() {
        return !usuarios.isEmpty();
    }

    public int getQuantidadeUsuarios() {
        return usuarios.size();
    }

    public int getQuantidadeEstudantes() {
        return (int) usuarios.stream()
                .filter(u -> u instanceof Estudante)
                .count();
    }

    public int getQuantidadeProfessores() {
        return (int) usuarios.stream()
                .filter(u -> u instanceof Professor)
                .count();
    }
}
