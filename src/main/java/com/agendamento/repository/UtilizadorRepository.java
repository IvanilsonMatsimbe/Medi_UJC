package com.agendamento.repository;

import com.agendamento.model.Utilizador;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizadorRepository extends JpaRepository<Utilizador, Integer> {
    Optional<Utilizador> findByEmail(String email);
    boolean existsByEmail(String email);
    List<Utilizador> findByTipo(Utilizador.TipoUtilizador tipo);
}