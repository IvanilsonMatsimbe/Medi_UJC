// AuthController.java
package com.agendamento.controller;

import com.agendamento.exception.ConflictException;
import com.agendamento.model.Utilizador;
import com.agendamento.service.UtilizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UtilizadorService utilizadorService;

    @Operation(summary = "Registrar novo Utilizador", description = "Este endpoint permite o registo de novos utilizadores no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),           
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
        @ApiResponse(responseCode = "409", description = "Usuário já existe"),
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody Utilizador utilizador) {
        // O serviço já verifica o e-mail, então esta verificação pode ser removida
        Utilizador savedUser = utilizadorService.salvar(utilizador);
        return ResponseEntity.ok(savedUser);
    }
}