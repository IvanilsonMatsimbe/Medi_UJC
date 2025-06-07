package com.agendamento.controller;

import com.agendamento.model.Consulta;
import com.agendamento.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    @Autowired
    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.agendarConsulta(consulta));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelarConsulta(
        @PathVariable Integer id, 
        @RequestParam String motivo
    ) {
        return ResponseEntity.ok(consultaService.cancelarConsulta(id, motivo));
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consulta>> listarPorMedico(@PathVariable Integer medicoId) {
        return ResponseEntity.ok(consultaService.listarConsultasPorMedico(medicoId));
    }
}