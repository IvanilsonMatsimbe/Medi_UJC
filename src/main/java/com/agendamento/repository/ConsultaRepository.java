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
    List<Consulta> findByMedicoIdAndDataHoraInicioBetween(
        Integer medicoId, LocalDateTime inicio, LocalDateTime fim);
    
    // Novo método para encontrar consultas por intervalo de tempo
    List<Consulta> findByDataHoraInicioBetween(
        LocalDateTime inicio, LocalDateTime fim);
    
    // Novo método para encontrar consultas por intervalo de tempo e médico
}