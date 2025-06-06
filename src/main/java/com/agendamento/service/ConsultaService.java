package com.agendamento.service;

import com.agendamento.model.Consulta;
import java.util.List;

public interface ConsultaService {
    Consulta agendarConsulta(Consulta consulta);
    Consulta cancelarConsulta(Integer id, String motivo);
    List<Consulta> listarTodasConsultas();
    List<Consulta> listarConsultasPorMedicoId(Integer medicoId);
    Consulta buscarConsultaPorId(Integer id);
}
