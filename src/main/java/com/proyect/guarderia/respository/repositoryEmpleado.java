package com.proyect.guarderia.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.guarderia.model.Empleado;

public interface repositoryEmpleado extends JpaRepository<Empleado, Integer>{

}
