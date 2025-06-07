package com.agendamento.repository;

import com.agendamento.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
    List<Consulta> findByPacienteId(Integer pacienteId);
    List<Consulta> findByMedicoId(Integer medicoId);
    List<Consulta> findByStatus(Consulta.StatusConsulta status);
    
    // Alterado para retornar lista em vez de boolean
    List<Consulta> findByMedicoIdAndDataHoraInicioBetween(
        Integer medicoId, LocalDateTime inicio, LocalDateTime fim);
}