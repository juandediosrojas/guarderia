package com.proyect.guarderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.proyect.guarderia.model.Mascota;
import com.proyect.guarderia.respository.repository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class MascotaController {

    @Autowired
    private repository repository;

    @GetMapping("/mascotas")
    public java.util.List<Mascota> all(){return repository.findAll();}
    
}
