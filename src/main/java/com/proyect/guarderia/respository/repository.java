package com.proyect.guarderia.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.proyect.guarderia.model.Mascota;

public interface repository extends JpaRepository<Mascota, Integer>{

    // List<Mascota> findByNombreMascota(@Param("nombreMascota") String nombreMascota);
}
