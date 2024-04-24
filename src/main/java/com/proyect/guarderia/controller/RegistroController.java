package com.proyect.guarderia.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyect.guarderia.model.Mascota;
import com.proyect.guarderia.model.Registro;
import com.proyect.guarderia.respository.repository;
import com.proyect.guarderia.respository.repositoryRegistro;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
public class RegistroController {

    @Autowired
    private repository mascotaRepository;

    @Autowired
    private repositoryRegistro repositoryRegistro;

    @GetMapping("/registros")
    public java.util.List<Registro> all() {
        return repositoryRegistro.findAll();
    }

    @PostMapping("/registros")
    public ResponseEntity<Object> createRegistro(@RequestBody String body) {
        if (body != null) {
            String cleanBody = body.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\"", "");
            // Dividir el JSON en partes separadas por el símbolo ":"
            String[] parts = cleanBody.split(":");
            // Obtener el valor después del símbolo ":"
            String value = parts[1].trim();
            // Convertir el valor a entero
            int mascotaId = Integer.parseInt(value);

            Mascota mascota = mascotaRepository.findByDk(mascotaId);
            if (mascota != null) {
                Registro registro = new Registro();
                registro.setMascota_fk(mascota);
                registro.setFechaEntrada(new Date());
                try {
                    repositoryRegistro.save(registro);
                    return ResponseEntity
                            .ok("La mascota con identificacion " + mascota.getIdentifiacion() + " ha sido ingresada.");
                } catch (DataIntegrityViolationException e) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La mascota con identificacion "
                            + mascota.getIdentifiacion() + " ya tiene un registro activo.");
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Ha ocurrido un error interno en el servidor");
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La mascota no existe.");
            }

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El cuerpo de la solicitud no puede estar vacío.");
        }
    }

    @DeleteMapping("/registros/{dk}")
    public ResponseEntity<Object> deleteMascota(@PathVariable String dk) {
        Registro registro = repositoryRegistro.findByDk(Integer.parseInt(dk));
        if (registro != null) {
            try {
                repositoryRegistro.delete(registro);
                return ResponseEntity
                        .ok("La mascota " + registro.getMascota_fk().getNombre() + " ha salido de la guardería");
            } catch (DataIntegrityViolationException e) {
                String mensajeError = "No se puede dar salida a la " + registro.getMascota_fk().getNombre()
                        + " porque está asociado a un registro.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al dar salida a la mascota");
            }
        } else {
            String mensajeError = "La mascota no  existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
}
