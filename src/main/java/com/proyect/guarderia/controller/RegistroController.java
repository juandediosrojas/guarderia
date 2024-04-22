package com.proyect.guarderia.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyect.guarderia.model.Registro;
import com.proyect.guarderia.respository.repositoryRegistro;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class RegistroController {

    private ResponseEntity<Map<String, Object>> createResponse(String status, HttpStatus code, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);
        response.put("code", code.value());
        return ResponseEntity.ok(response);
    }
    

    @Autowired
    private repositoryRegistro repositoryRegistro;

    @GetMapping("/registros")
    public java.util.List<Registro> all() {
        return repositoryRegistro.findAll();
    }

    // @PostMapping("/registros")
    // public ResponseEntity<Object> createRegistro(@RequestBody String body) {
        
    // }

    @DeleteMapping("/registros/{dk}")
    public ResponseEntity<Object> deleteMascota(@PathVariable String dk) {
        Registro registro = repositoryRegistro.findByDk(Integer.parseInt(dk));
        if (registro != null) {
            try {
                repositoryRegistro.delete(registro);
                return ResponseEntity.ok("La mascota " + registro.getMascota_fk().getNombre() + " ha salido de la guardería");
            } catch (DataIntegrityViolationException e) {
                String mensajeError = "No se puede dar salida a la " + registro.getMascota_fk().getNombre() 
                    + " porque está asociado a un registro.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar salida a la mascota");
            }
        } else {
            String mensajeError = "La mascota no se existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }       
    }
}
