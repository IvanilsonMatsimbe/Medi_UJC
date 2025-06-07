package com.agendamento.service;

import com.agendamento.model.Utilizador;
import java.util.List;
import java.util.Optional;

public interface userser {
    Utilizador salvar(Utilizador utilizador);
    List<Utilizador> listarTodos();
    Optional<Utilizador> buscarPorId(Long id); // Keep Long if needed
    void remover(Long id);
}