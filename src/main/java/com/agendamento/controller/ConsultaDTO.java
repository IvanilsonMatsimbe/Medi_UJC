package com.agendamento.controller;

import com.agendamento.model.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ConsultaDTO(
    @NotNull(message = "ID do paciente é obrigatório")
    Integer pacienteId,
    
    @NotNull(message = "ID do médico é obrigatório")
    Integer medicoId,
    
    @NotNull(message = "Data/hora de início é obrigatória")
    @Future(message = "Data/hora de início deve ser no futuro")
    LocalDateTime dataHoraInicio,
    
    @NotNull(message = "Data/hora de fim é obrigatória")
    @Future(message = "Data/hora de fim deve ser no futuro")
    LocalDateTime dataHoraFim,
    
    String observacoes
) {
    public Consulta toEntity() {
        Consulta consulta = new Consulta();
        consulta.setDataHoraInicio(dataHoraInicio);
        consulta.setDataHoraFim(dataHoraFim);
        consulta.setObservacoes(observacoes);
        return consulta;
    }
}