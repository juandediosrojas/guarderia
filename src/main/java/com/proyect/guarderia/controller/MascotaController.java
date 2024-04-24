package com.proyect.guarderia.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.proyect.guarderia.model.Mascota;
import com.proyect.guarderia.respository.repository;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MascotaController {

    @Autowired
    private repository repository;

    @GetMapping("/mascotas")
    public java.util.List<Mascota> all(){return repository.findAll();}

    @GetMapping("/mascotas/{identificacion}")
    public Mascota mascota(@PathVariable String identificacion){
        return repository.findByIdentifiacion(Integer.parseInt(identificacion));
    }

    @GetMapping("/mascotasSinRegistro")
    public java.util.List<Mascota> mascotasSinRegistros(){
        return repository.findMascotasWithNullRegistro();
    }

    @PostMapping("/mascotas")
    public ResponseEntity<Object> createMascota(@RequestBody Mascota mascota) {
        try {            
            Mascota nuevoMascota = repository.save(mascota);
            return ResponseEntity.ok("La mascota " + nuevoMascota.getNombre() + " ha sido creado con éxito");
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("La mascota con identificacion "+ mascota.getIdentifiacion() +"  ya existe. Por favor verifique e intente de nuevo");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Ha ocurrido un error interno en el servidor");
            }
        }
    }
    
    @PutMapping("/mascotas/{identificacion}")
    public ResponseEntity<Object> updateMascota(@PathVariable String identificacion, @RequestBody Mascota mascota) {
        Mascota existingMascota = repository.findByIdentifiacion(Integer.parseInt(identificacion));
        if (existingMascota != null) {

            existingMascota.setNombre(mascota.getNombre());
            existingMascota.setEspecie(mascota.getEspecie());
            existingMascota.setRaza(mascota.getRaza());
            existingMascota.setCliente_fk(mascota.getCliente_fk());;

            Mascota updatedCliente = repository.save(existingMascota);
            return ResponseEntity.ok(updatedCliente);
        } else {
            String mensajeError = "Las mascota con identificación " + identificacion + " no se encontró.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @DeleteMapping("/mascotas/{dk}")
    public ResponseEntity<Object> deleteMascota(@PathVariable String dk) {
        Mascota mascota = repository.findByDk(Integer.parseInt(dk));
        if (mascota != null) {
            try {
                repository.delete(mascota);
                return ResponseEntity.ok("La mascota " + mascota.getNombre() + " ha sido eliminado");
            } catch (DataIntegrityViolationException e) {
                String mensajeError = "No se puede eliminar la mascota con identificación " + mascota.getIdentifiacion() 
                    + " porque está asociado a un registro.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el cliente");
            }
        } else {
            String mensajeError = "La mascota no se existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }       
    }
    
}
