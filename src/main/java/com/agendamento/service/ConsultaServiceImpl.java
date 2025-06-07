package com.agendamento.service;

import com.agendamento.exception.ConflictException;
import com.agendamento.exception.ResourceNotFoundException;
import com.agendamento.model.Consulta;
import com.agendamento.model.Utilizador;
import com.agendamento.repository.ConsultaRepository;
import com.agendamento.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UtilizadorRepository utilizadorRepository;

    @Autowired
    public ConsultaServiceImpl(ConsultaRepository consultaRepository, 
                               UtilizadorRepository utilizadorRepository) {
        this.consultaRepository = consultaRepository;
        this.utilizadorRepository = utilizadorRepository;
    }

    @Override
    public Consulta agendarConsulta(Consulta consulta) {
        Utilizador medico = utilizadorRepository.findById(consulta.getMedico().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        
        if (!verificarDisponibilidadeMedico(medico, consulta.getDataHoraInicio(), consulta.getDataHoraFim())) {
            throw new ConflictException("Médico não disponível neste horário");
        }
        return consultaRepository.save(consulta);
    }

    @Override
    public Consulta cancelarConsulta(Integer id, String motivo) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        consulta.setStatus(Consulta.StatusConsulta.CANCELADA);
        consulta.setObservacoes(motivo);
        return consultaRepository.save(consulta);
    }

    @Override
    public Consulta confirmarConsulta(Integer id) {
        Consulta consulta = consultaRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada"));
        consulta.setConfirmacaoPresenca(true);
        return consultaRepository.save(consulta);
    }

    @Override
    public List<Consulta> listarConsultasPorMedico(Integer medicoId) {
        return consultaRepository.findByMedicoId(medicoId);
    }

    @Override
    public List<Consulta> listarConsultasPorPaciente(Integer pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId);
    }

    @Override
    public boolean verificarDisponibilidadeMedico(Utilizador medico, LocalDateTime inicio, LocalDateTime fim) {
        return consultaRepository.findByMedicoIdAndDataHoraInicioBetween(medico.getId(), inicio, fim).isEmpty();
    }
}