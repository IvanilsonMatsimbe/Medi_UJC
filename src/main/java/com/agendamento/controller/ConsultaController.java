// ConsultaController.java
package com.agendamento.controller;

import com.agendamento.model.Consulta;
import com.agendamento.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Agendar consulta", description = "Este endpoint permite agendar uma nova consulta médica.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta agendada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos para agendamento")
    })
    @PostMapping
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.agendarConsulta(consulta));
    }

    @Operation(summary = "Cancelar consulta", description = "Este endpoint permite cancelar uma consulta existente pelo seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta cancelada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
        @ApiResponse(responseCode = "400", description = "Motivo inválido para cancelamento")
    })
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelarConsulta(
        @PathVariable Integer id, 
        @RequestParam String motivo
    ) {
        return ResponseEntity.ok(consultaService.cancelarConsulta(id, motivo));
    }

    @Operation(summary = "Listar consultas por médico", description = "Este endpoint permite retornar uma lista de consultas agendadas para um médico específico.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consulta>> listarPorMedico(@PathVariable Integer medicoId) {
        return ResponseEntity.ok(consultaService.listarConsultasPorMedico(medicoId));
    }
}