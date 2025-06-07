package com.agendamento.service.impl;

import com.agendamento.exceptions.ConflictException;
import com.agendamento.model.Consulta;
import com.agendamento.model.Utilizador;
import com.agendamento.repository.ConsultaRepository;
import com.agendamento.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;

    @Override
    @Transactional
    public Consulta agendarConsulta(Consulta consulta) {
        if (!verificarDisponibilidadeMedico(
            consulta.getMedico(), 
            consulta.getData_hora_inicio(), 
            consulta.getData_hora_fim()
        )) {
            throw new ConflictException("Médico não disponível neste horário");
        }
        return consultaRepository.save(consulta);
    }

    @Override
    public boolean verificarDisponibilidadeMedico(
        Utilizador medico, 
        LocalDateTime inicio, 
        LocalDateTime fim
    ) {
        return !consultaRepository.existsByMedicoIdAndDataHoraInicioBetween(
            medico.getId(), inicio, fim);
    }
    
    // Outros métodos implementados similarmente
}