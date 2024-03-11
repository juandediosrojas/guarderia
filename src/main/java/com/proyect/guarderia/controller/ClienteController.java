package com.proyect.guarderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.guarderia.model.Cliente;
import com.proyect.guarderia.respository.respositoryCliente;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private respositoryCliente repository;

    @GetMapping("/clientes")
    public java.util.List<Cliente> all(){return repository.findAll();}
    
}
