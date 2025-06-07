package com.agendamento.dto;

import java.time.LocalDateTime;

public record ConsultaDTO(
    Integer pacienteId,
    Integer medicoId,
    LocalDateTime dataHoraInicio,
    LocalDateTime dataHoraFim,
    String observacoes
) {}