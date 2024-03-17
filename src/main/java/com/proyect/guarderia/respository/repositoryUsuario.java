package com.proyect.guarderia.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.guarderia.model.Usuario;

public interface repositoryUsuario extends JpaRepository<Usuario, Integer>{

    Usuario findByUsuarioAndPassword(String nombre_usuario, String password);

    Usuario findByUsuario(String u);

}
