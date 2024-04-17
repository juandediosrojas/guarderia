package com.proyect.guarderia.respository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.proyect.guarderia.model.Registro;
import java.util.List;

public interface repositoryRegistro extends JpaRepository<Registro, Integer>{
    List<Registro> findByFechaSalidaIsNull();
}
