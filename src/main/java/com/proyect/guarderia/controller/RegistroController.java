package com.proyect.guarderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.guarderia.model.Registro;
import com.proyect.guarderia.respository.repositoryRegistro;

@RestController
@RequestMapping("/api")
public class RegistroController {

    @Autowired
    private repositoryRegistro repository;

    @GetMapping("/registros")
    public java.util.List<Registro> all(){return repository.findAll();}
}
