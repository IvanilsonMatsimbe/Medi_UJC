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
    
    @Column(nullable = false)
    private LocalDateTime data_hora_inicio;
    
    @Column(nullable = false)
    private LocalDateTime data_hora_fim;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta status = StatusConsulta.AGENDADA;
    
    private String observacoes;
    
    @Column(nullable = false)
    private Boolean confirmacao_presenca = false;
    
    public enum StatusConsulta {
        AGENDADA, REALIZADA, CANCELADA, REMARCADA, CONFIRMADA
    }
}