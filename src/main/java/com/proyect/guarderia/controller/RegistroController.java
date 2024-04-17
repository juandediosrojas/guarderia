package com.proyect.guarderia.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
public class RegistroController {

    @Autowired
    private repositoryRegistro repositoryRegistro;

    @GetMapping("/registros")
    public java.util.List<Registro> registrosSinSalida() {
        return repositoryRegistro.findByFechaSalidaIsNull();
    }

    @PutMapping("/registros/{dk}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable int dk, @RequestBody String requestBody) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> requestData = mapper.readValue(requestBody, Map.class);
        String fechaSalidaString = (String) requestData.get("fecha_salida");
        
        System.out.println("Fecha de salida: " + fechaSalidaString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha_salida = null;
        try {
            fecha_salida = new Date(dateFormat.parse(fechaSalidaString).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Fecha de salida: " + fecha_salida);

        Optional<Registro> registro = repositoryRegistro.findById(dk);
        if (!registro.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Registro no encontrado");
            response.put("code", HttpStatus.NOT_FOUND.value());
            return ResponseEntity.ok(response);
        } else {
            Registro r = repositoryRegistro.findById(dk).get();
            r.setFechaSalida(fecha_salida);
            Registro resgistroGuardado = repositoryRegistro.save(r);
            if (resgistroGuardado != null) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "OK");
                response.put("message", "Salida registrada correctamente");
                response.put("code", HttpStatus.OK.value());
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Error al registrar la salida");
                response.put("code", HttpStatus.INTERNAL_SERVER_ERROR.value());
                return ResponseEntity.ok(response);
            }
        }
    }
}
