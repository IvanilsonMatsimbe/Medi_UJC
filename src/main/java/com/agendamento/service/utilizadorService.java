package com.agendamento.service;

import com.agendamento.model.Utilizador;
import java.util.List;
import java.util.Optional;

public interface UtilizadorService {
    Utilizador salvar(Utilizador utilizador);
    List<Utilizador> listarTodos();
    Optional<Utilizador> buscarPorId(Integer id);
    Utilizador atualizar(Integer id, Utilizador utilizador);
    void remover(Integer id);
    boolean existsByEmail(String email);
    List<Utilizador> listarPorTipo(Utilizador.TipoUtilizador tipo);
     
}