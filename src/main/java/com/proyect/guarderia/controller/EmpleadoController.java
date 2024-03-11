package com.proyect.guarderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.guarderia.model.Empleado;
import com.proyect.guarderia.respository.repositoryEmpleado;

@RestController
@RequestMapping("/api")
public class EmpleadoController {
      
    @Autowired
    private repositoryEmpleado repository;

    @GetMapping("/empleados")
    public java.util.List<Empleado> all(){return repository.findAll();}
    
}
