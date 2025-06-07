package com.agendamento.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class Disponibilidade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Utilizador medico;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DiaSemana dia_semana;
    
    @Column(nullable = false)
    private LocalTime horario_inicio;
    
    @Column(nullable = false)
    private LocalTime horario_fim;
    
    public enum DiaSemana {
        SEG, TER, QUA, QUI, SEX, SAB, DOM
    }
    
}