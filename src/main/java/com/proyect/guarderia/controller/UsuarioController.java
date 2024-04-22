package com.proyect.guarderia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.mindrot.jbcrypt.BCrypt;

import com.proyect.guarderia.model.Usuario;
import com.proyect.guarderia.respository.repositoryUsuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UsuarioController {
    @Autowired
    private repositoryUsuario repository;

    private static final String SECRET_KEY = "qbk6qk>1Z79fW8tS*ZARL{C93%!tVDu)";

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Usuario usuario) {
        String u = usuario.getUsuario();
        String p = usuario.getPassword();

        Usuario user = repository.findByUsuario(u);              
        if (user !=null  && BCrypt.checkpw(p, user.getPassword())) {
            
            String token = generateJwtToken(user.getDk());
            // Construir la respuesta con el token
            Map<String, Object> response = new HashMap<>();
            response.put("status", "OK");
            response.put("message", "Usuario autenticado correctamente");
            response.put("code", HttpStatus.OK.value());
            response.put("token", token);
            response.put("usuario", user);
            return ResponseEntity.ok(response);
        } else {
            // Construir la respuesta de error
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Contraseña incorrecta o usuario no existe, por favor verifique e intente de nuevo");
            response.put("code", HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.ok(response);
        }
    }

    private String generateJwtToken(int dk) {
        Date now = new Date();
            Date expiryDate = new Date(now.getTime() + 864000000); 
            SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
            
            return Jwts.builder()
                    .setSubject(Integer.toString(dk))
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(key)
                    .compact();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody Usuario usuario) {
        String u = usuario.getUsuario();
        String p = usuario.getPassword();

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
            newUser.setEmpleado_fk(usuario.getEmpleado_fk());

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