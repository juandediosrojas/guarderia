package com.proyect.guarderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.mindrot.jbcrypt.BCrypt;

import com.proyect.guarderia.model.Usuario;
import com.proyect.guarderia.respository.repositoryUsuario;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UsuarioController {
    @Autowired
    private repositoryUsuario repository;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Usuario usuario) {
        String u = usuario.getUsuario();
        String p = usuario.getPassword();

        Usuario user = repository.findByUsuario(u);              
        if (user !=null  && BCrypt.checkpw(p, user.getPassword())) {
            Object response = new Object() {
                public final String status = "OK";
                public final String message = "Usuario autenticado correctamente";
                public final int code = HttpStatus.ACCEPTED.value();
            };
            return ResponseEntity.ok(response);
        } else {
            Object response = new Object() {
                public final String status = "error";
                public final String message = p + "\n" + user.getPassword();
                public final int code = HttpStatus.UNAUTHORIZED.value();
            };
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Usuario usuario) {
        String u = usuario.getUsuario();
        String p = usuario.getPassword();
        int idEmpleado = usuario.getEmpleado_fk();

        try {
            Usuario existingUser = repository.findByUsuario(u);
            if (existingUser != null) {
                Object response = new Object() {
                    public final String status = "error";
                    public final String message = "El nombre de usuario ya está en uso";
                    public final int code = HttpStatus.ACCEPTED.value();
                };
                return ResponseEntity.ok(response);
            }

            String hashedPassword = BCrypt.hashpw(p, BCrypt.gensalt());

            // Create a new user
            Usuario newUser = new Usuario();
            newUser.setUsuario(u);
            newUser.setPassword(hashedPassword);
            newUser.setEmpleado_fk(idEmpleado);

            // Save the user to the repository
            repository.save(newUser);
            Object response = new Object() {
                public final String status = "OK";
                public final String message = "Usuario registrado correctamente";
                public final int code = HttpStatus.CREATED.value();
            };
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }

}