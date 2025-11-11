package controller;

import enums.StatusReserva;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Livro;
import model.Reserva;
import model.Usuario;

public class GerenciadorReservas {
    private List<Reserva> reservas;

    public GerenciadorReservas() {
        this.reservas = new ArrayList<>();
    }

    public void fazerReserva(Usuario usuario, Livro livro, String dataReserva) {
        if (usuario == null || livro == null) {
            throw new IllegalArgumentException("Usuário e livro não podem ser nulos");
        }

        boolean jaExisteReservaAtiva = reservas.stream()
                .anyMatch(r -> r.getUsuario().equals(usuario)
                        && r.getLivro().equals(livro)
                        && r.isAtiva());

        if (jaExisteReservaAtiva) {
            throw new IllegalArgumentException("Usuário já possui uma reserva ativa para este livro");
        }

        Reserva reserva = new Reserva(usuario, livro, dataReserva);
        reservas.add(reserva);
    }

    public List<Reserva> buscarPorUsuario(Usuario usuario) {
        return reservas.stream()
                .filter(r -> r.getUsuario().equals(usuario))
                .collect(Collectors.toList());
    }

    public List<Reserva> buscarPorLivro(Livro livro) {
        return reservas.stream()
                .filter(r -> r.getLivro().equals(livro))
                .collect(Collectors.toList());
    }

    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    public List<Reserva> listarReservasAtivas() {
        return reservas.stream()
                .filter(r -> r.getStatus() == StatusReserva.ATIVA)
                .collect(Collectors.toList());
    }

    public void atualizarStatus(Reserva reserva, StatusReserva status) {
        reserva.setStatus(status);
    }

    public void removerReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public int getQuantidadeReservas() {
        return reservas.size();
    }
}
