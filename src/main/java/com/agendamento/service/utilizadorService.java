package com.agendamento.service;

import com.agendamento.model.Utilizador;

import java.util.List;
import java.util.Optional;

public interface UtilizadorService {
    Utilizador salvar(Utilizador utilizador);
    List<Utilizador> listarTodos();
    Optional<Utilizador> buscarPorId(Long id);
    void remover(Long id);
}
