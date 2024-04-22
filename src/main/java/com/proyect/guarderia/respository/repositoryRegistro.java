package com.proyect.guarderia.respository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.guarderia.model.Registro;

public interface repositoryRegistro extends JpaRepository<Registro, Integer>{   
    Registro findByDk(int dk);
}
