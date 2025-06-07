package com.agendamento.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Utilizador paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Utilizador medico;
    
    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;
    
    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime dataHoraFim;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status = StatusConsulta.AGENDADA;
    
    private String observacoes;
    
    @Column(name = "confirmacao_presenca", nullable = false)
    private Boolean confirmacaoPresenca = false;
    
    public enum StatusConsulta {
        AGENDADA, REALIZADA, CANCELADA, REMARCADA, CONFIRMADA
    }
}