package com.proyect.guarderia.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyect.guarderia.model.Mascota;

public interface repository extends JpaRepository<Mascota, Integer>{

     Mascota findByDk(int dk);
     
     @Query("SELECT m FROM Mascota m WHERE NOT EXISTS (SELECT r FROM Registro r WHERE r.mascota = m)")
     List<Mascota> findMascotasWithNullRegistro();
     
  }