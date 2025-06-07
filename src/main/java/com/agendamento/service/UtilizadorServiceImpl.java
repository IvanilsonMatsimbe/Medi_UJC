package com.agendamento.service;

import com.agendamento.model.Utilizador;
import com.agendamento.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizadorServiceImpl implements UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UtilizadorServiceImpl(UtilizadorRepository utilizadorRepository, 
                                PasswordEncoder passwordEncoder) {
        this.utilizadorRepository = utilizadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Utilizador salvar(Utilizador utilizador) {
        // Encrypt password before saving
        utilizador.setSenha(passwordEncoder.encode(utilizador.getSenha()));
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
}