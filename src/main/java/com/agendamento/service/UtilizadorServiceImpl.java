package com.agendamento.service;

import com.agendamento.exception.ConflictException;
import com.agendamento.exception.ResourceNotFoundException;
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
        if (utilizador.getId() == null && existsByEmail(utilizador.getEmail())) {
            throw new ConflictException("Email já registrado");
        }
        return utilizadorRepository.save(utilizador);
    }

    @Override
    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    @Override
    public Optional<Utilizador> buscarPorId(Integer id) {
        return utilizadorRepository.findById(id);
    }

    @Override
    public Utilizador atualizar(Integer id, Utilizador utilizador) {
        return utilizadorRepository.findById(id)
                .map(existing -> {
                    utilizador.setId(id);
                    return utilizadorRepository.save(utilizador);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    @Override
    public void remover(Integer id) {
        if (!utilizadorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        utilizadorRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return utilizadorRepository.existsByEmail(email);
    }

    @Override
    public List<Utilizador> listarPorTipo(Utilizador.TipoUtilizador tipo) {
        return utilizadorRepository.findByTipo(tipo);
    }


}