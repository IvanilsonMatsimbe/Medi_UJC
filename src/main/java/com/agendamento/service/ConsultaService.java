package com.agendamento.service;

import com.agendamento.model.Consulta;
import com.agendamento.model.Utilizador;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ConsultaService {
    // Operações CRUD
    Consulta agendarConsulta(Consulta consulta);
    List<Consulta> listarTodas();
    Optional<Consulta> buscarPorId(Integer id);
    Consulta atualizarConsulta(Integer id, Consulta consulta);
    void excluirConsulta(Integer id);
    
    // Operações específicas
    Consulta cancelarConsulta(Integer id, String motivo);
    Consulta confirmarConsulta(Integer id);
    List<Consulta> listarConsultasPorMedico(Integer medicoId);
    List<Consulta> listarConsultasPorPaciente(Integer pacienteId);
    boolean verificarDisponibilidadeMedico(Utilizador medico, LocalDateTime inicio, LocalDateTime fim);
}