package com.proyect.guarderia.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.guarderia.model.Cliente;

public interface respositoryCliente extends JpaRepository<Cliente, Integer>{

    Cliente findByIdentificacion(String identificacion);
}
