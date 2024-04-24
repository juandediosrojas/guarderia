package com.proyect.guarderia.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.guarderia.model.Cliente;
import com.proyect.guarderia.respository.respositoryCliente;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE })
public class ClienteController {

    @Autowired
    private respositoryCliente repository;

    @GetMapping("/clientes")
    public java.util.List<Cliente> all(){return repository.findAll();}

    @GetMapping("/clientes/{identificacion}")
    public ResponseEntity<Object> getCliente(@PathVariable String identificacion) {
        Cliente cliente = repository.findByIdentificacion(identificacion);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            String mensajeError = "El cliente con identificación " + identificacion + " no se encontró.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    @GetMapping("/clientes/dk/{dk}")
    public ResponseEntity<Object> getClienteDk(@PathVariable String dk){
        Cliente cliente = repository.findByDk(Integer.parseInt(dk));
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            String mensajeError = "El cliente con identificación " + dk + " no se encontró.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }
    
    @PutMapping("/clientes/{identificacion}")
    public ResponseEntity<Object> updateCliente(@PathVariable String identificacion, @RequestBody Cliente cliente) {
        Cliente existingCliente = repository.findByIdentificacion(identificacion);
        if (existingCliente != null) {

            existingCliente.setNombres_cliente(cliente.getNombres_cliente());
            existingCliente.setApellidos_cliente(cliente.getApellidos_cliente());
            existingCliente.setDireccion(cliente.getDireccion());
            existingCliente.setCorreo(cliente.getCorreo());
            existingCliente.setCelular(cliente.getCelular());

            Cliente updatedCliente = repository.save(existingCliente);
            return ResponseEntity.ok(updatedCliente);
        } else {
            String mensajeError = "El cliente con identificación " + identificacion + " no se encontró.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<Object> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = repository.save(cliente);
            return ResponseEntity.ok(nuevoCliente);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El cliente con cedula "+ cliente.getIdentificacion() +"  ya existe. Por favor verifique e intente de nuevo");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Ha ocurrido un error interno en el servidor");
            }
        }
    }

    @DeleteMapping("/clientes/{identificacion}")
    public ResponseEntity<Object> deleteCliente(@PathVariable String identificacion) {
        Cliente cliente = repository.findByIdentificacion(identificacion);
        if (cliente != null) {
            try {
                repository.delete(cliente);
                return ResponseEntity.ok("El cliente con identificación " + identificacion + " ha sido eliminado");
            } catch (DataIntegrityViolationException e) {
                String mensajeError = "No se puede eliminar el cliente con identificación " + identificacion 
                    + " porque está asociado a una mascota. Elimine primero la mascota asociada y vuelva a intentarlo.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el cliente");
            }
        } else {
            String mensajeError = "El cliente con identificación " + identificacion + " no se encontró.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
        }       
    }
}