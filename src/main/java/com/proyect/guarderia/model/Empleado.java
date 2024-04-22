package com.proyect.guarderia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="empleado")
public class Empleado {

    @Id
    private int dk;
    private String identificacion_empleado;
    private String nombres_empleado;
    private String apellidos_empleado;
    private String direccion;
    private String correo;
    private String celular;
    
    public Empleado() {
    }

    public Empleado(int dk, String identificacion_empleado, String nombres_empleado, String apellidos_empleado,
            String direccion, String correo, String celular) {
        this.dk = dk;
        this.identificacion_empleado = identificacion_empleado;
        this.nombres_empleado = nombres_empleado;
        this.apellidos_empleado = apellidos_empleado;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
    }

    public int getDk() {
        return dk;
    }

    public void setDk(int dk) {
        this.dk = dk;
    }

    public String getIdentificacion_empleado() {
        return identificacion_empleado;
    }

    public void setIdentificacion_empleado(String identificacion_empleado) {
        this.identificacion_empleado = identificacion_empleado;
    }

    public String getNombres_empleado() {
        return nombres_empleado;
    }

    public void setNombres_empleado(String nombres_empleado) {
        this.nombres_empleado = nombres_empleado;
    }

    public String getApellidos_empleado() {
        return apellidos_empleado;
    }

    public void setApellidos_empleado(String apellidos_empleado) {
        this.apellidos_empleado = apellidos_empleado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    
}
