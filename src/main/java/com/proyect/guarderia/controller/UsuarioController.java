package com.proyect.guarderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.guarderia.model.Usuario;
import com.proyect.guarderia.respository.repositoryUsuario;

@RestController
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    private repositoryUsuario repository;

    @GetMapping("/usuarios")
    public java.util.List<Usuario> all() {
        return repository.findAll();
    }

    
}
