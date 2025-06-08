// UtilizadorServiceImpl.java
package com.agendamento.service;

import com.agendamento.exception.ConflictException;
import com.agendamento.model.Utilizador;
import com.agendamento.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorServiceImpl implements UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    @Autowired
    public UtilizadorServiceImpl(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    @Override
    public Utilizador salvar(Utilizador utilizador) {
        if (existsByEmail(utilizador.getEmail())) {
            throw new ConflictException("Email já registrado");
        }
        return utilizadorRepository.save(utilizador);
    }

    @Override
    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    @Override
    public Optional<Utilizador> buscarPorId(Long id) {
        return utilizadorRepository.findById(id.intValue());
    }

    @Override
    public void remover(Long id) {
        utilizadorRepository.deleteById(id.intValue());
    }
    @Override
    public boolean existsByEmail(String email) {
        return utilizadorRepository.existsByEmail(email);
    }
}