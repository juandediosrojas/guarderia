package com.proyect.guarderia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    private int dk;
    private String usuario; 
    private String password;
    private int empleado_fk;

    public Usuario() {
    }

    public Usuario(int dk, String usuario, String password, int empleado_fk) {
        this.dk = dk;
        this.usuario = usuario;
        this.password = password;
        this.empleado_fk = empleado_fk;
    }

    public int getDk() {
        return dk;
    }

    public void setDk(int dk) {
        this.dk = dk;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
