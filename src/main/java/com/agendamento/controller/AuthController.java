package com.agendamento.controller;

import com.agendamento.model.Utilizador;
import com.agendamento.service.UtilizadorService;
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

    @PostMapping("/register")
    public ResponseEntity<Utilizador> registerUser(@RequestBody Utilizador utilizador) {
        Utilizador savedUser = utilizadorService.salvar(utilizador);
        return ResponseEntity.ok(savedUser);
    }
}