package com.agendamento.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String nome;
    
    private String descricao;
 
    
}