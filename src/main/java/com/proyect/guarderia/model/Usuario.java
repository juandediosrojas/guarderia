package com.proyect.guarderia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    private int usuario_dk;
    private String nombre_usuario; 
    private String password;
    private int empleado_fk;

    public Usuario() {
    }

    public Usuario(int usuario_dk, String nombre_usuario, String password, int empleado_fk) {
        this.usuario_dk = usuario_dk;
        this.nombre_usuario = nombre_usuario;
        this.password = password;
        this.empleado_fk = empleado_fk;
    }

    public int getUsuario_dk() {
        return usuario_dk;
    }

    public void setUsuario_dk(int usuario_dk) {
        this.usuario_dk = usuario_dk;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmpleado_fk() {
        return empleado_fk;
    }

    public void setEmpleado_fk(int empleado_fk) {
        this.empleado_fk = empleado_fk;
    }

    
}
