package com.agendamento.service;

import com.agendamento.model.Consulta;
import com.agendamento.model.Utilizador;
import java.time.LocalDateTime;
import java.util.List;
 
public interface ConsultaService {
    Consulta agendarConsulta(Consulta consulta);
    Consulta cancelarConsulta(Integer id, String motivo);
    Consulta confirmarConsulta(Integer id);
    List<Consulta> listarConsultasPorMedico(Integer medicoId);
    List<Consulta> listarConsultasPorPaciente(Integer pacienteId);
    boolean verificarDisponibilidadeMedico(Utilizador medico, LocalDateTime inicio, LocalDateTime fim);
}