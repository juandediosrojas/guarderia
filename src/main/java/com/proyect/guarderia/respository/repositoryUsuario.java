package com.proyect.guarderia.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.guarderia.model.Usuario;

public interface repositoryUsuario extends JpaRepository<Usuario, Integer>{

}
