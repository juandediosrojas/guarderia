package com.proyect.guarderia.model;

import jakarta.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dk;
    private String identificacion;
    private String nombres_cliente;
    private String apellidos_cliente;
    private String direccion;
    private String correo;
    private long celular;
    
    public Cliente(String identificacion_cliente, String nombres_cliente, String apellidos_cliente,
            String direccion, String correo, long celular) {
        this.identificacion = identificacion_cliente;
        this.nombres_cliente = nombres_cliente;
        this.apellidos_cliente = apellidos_cliente;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
    }

    public Cliente() {
    }

    public int getDk() {
        return dk;
    }

    public void setDk(int cliente_dk) {
        this.dk = cliente_dk;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion_cliente) {
        this.identificacion = identificacion_cliente;
    }

    public String getNombres_cliente() {
        return nombres_cliente;
    }

    public void setNombres_cliente(String nombres_cliente) {
        this.nombres_cliente = nombres_cliente;
    }

    public String getApellidos_cliente() {
        return apellidos_cliente;
    }

    public void setApellidos_cliente(String apellidos_cliente) {
        this.apellidos_cliente = apellidos_cliente;
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

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

}
