package com.agendamento.controller;

import com.agendamento.exception.ResourceNotFoundException;
import com.agendamento.model.Consulta;
import com.agendamento.model.Utilizador;
import com.agendamento.service.ConsultaService;
import com.agendamento.service.UtilizadorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaService consultaService;
    private final UtilizadorService utilizadorService; // Adicione esta linha

    @Autowired
    public ConsultaController(
        ConsultaService consultaService,
        UtilizadorService utilizadorService // Injete via construtor
    ) {
        this.consultaService = consultaService;
        this.utilizadorService = utilizadorService; // Inicialize o serviço
    }

    // Operações CRUD básicas
    @Operation(summary = "Criar nova consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Consulta criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Conflito de horário")
    })
    // Método criarConsulta atualizado
    @PostMapping
    public ResponseEntity<Consulta> criarConsulta(@Valid @RequestBody ConsultaDTO consultaDTO) {
        // Busca o paciente e valida existência
        Utilizador paciente = utilizadorService.buscarPorId(consultaDTO.pacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
        
        // Busca o médico e valida existência
        Utilizador medico = utilizadorService.buscarPorId(consultaDTO.medicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
        
        // Converte DTO para entidade e atribui relacionamentos
        Consulta consulta = consultaDTO.toEntity();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        
        // Agenda a consulta
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(consultaService.agendarConsulta(consulta));
    }
    @Operation(summary = "Listar todas as consultas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }

    @Operation(summary = "Obter consulta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta encontrada"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(consultaService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada")));
    }

    @Operation(summary = "Atualizar consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizarConsulta(
            @PathVariable Integer id, 
            @RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.atualizarConsulta(id, consulta));
    }

    @Operation(summary = "Excluir consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Consulta excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirConsulta(@PathVariable Integer id) {
        consultaService.excluirConsulta(id);
        return ResponseEntity.noContent().build();
    }

    // Operações específicas existentes
    @Operation(summary = "Cancelar consulta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Consulta cancelada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Consulta não encontrada"),
        @ApiResponse(responseCode = "400", description = "Motivo inválido para cancelamento")
    })
    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Consulta> cancelarConsulta(
        @PathVariable Integer id, 
        @RequestParam String motivo) {
        return ResponseEntity.ok(consultaService.cancelarConsulta(id, motivo));
    }

    @Operation(summary = "Listar consultas por médico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Médico não encontrado")
    })
    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consulta>> listarPorMedico(@PathVariable Integer medicoId) {
        return ResponseEntity.ok(consultaService.listarConsultasPorMedico(medicoId));
    }

    @Operation(summary = "Listar consultas por paciente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de consultas retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Paciente não encontrado")
    })
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Consulta>> listarPorPaciente(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(consultaService.listarConsultasPorPaciente(pacienteId));
    }
}