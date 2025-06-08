package com.agendamento.controller;

import com.agendamento.exception.ResourceNotFoundException;
import com.agendamento.model.Utilizador;
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
@RequestMapping("/api/usuarios")
public class UtilizadorController {

    private final UtilizadorService utilizadorService;

    @Autowired
    public UtilizadorController(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    @Operation(summary = "Criar novo usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "409", description = "Email já registrado")
    })
    @PostMapping
    public ResponseEntity<Utilizador> criarUsuario(@Valid @RequestBody Utilizador utilizador) {
        Utilizador novoUsuario = utilizadorService.salvar(utilizador);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Utilizador>> listarTodos() {
        return ResponseEntity.ok(utilizadorService.listarTodos());
    }

    @Operation(summary = "Obter usuário por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Utilizador> buscarPorId(@PathVariable Integer id) {
        Utilizador utilizador = utilizadorService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return ResponseEntity.ok(utilizador);
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Utilizador> atualizarUsuario(
            @PathVariable Integer id, 
            @Valid @RequestBody Utilizador utilizador) {
        return ResponseEntity.ok(utilizadorService.atualizar(id, utilizador));
    }

    @Operation(summary = "Excluir usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Integer id) {
        utilizadorService.remover(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar usuários por tipo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Utilizador>> listarPorTipo(
            @PathVariable Utilizador.TipoUtilizador tipo) {
        return ResponseEntity.ok(utilizadorService.listarPorTipo(tipo));
    }
}