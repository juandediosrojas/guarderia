package com.proyect.guarderia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {

    @Id
    private int dk;
    private String usuario; 
    private String password;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_fk")
    private Empleado empleado;

    public Usuario() {
    }

    public Usuario(int dk, String usuario, String password, Empleado empleado) {
        this.dk = dk;
        this.usuario = usuario;
        this.password = password;
        this.empleado = empleado;
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

    public Empleado getEmpleado_fk() {
        return empleado;
    }

    public void setEmpleado_fk(Empleado empleado) {
        this.empleado = empleado;
    }


    
}
