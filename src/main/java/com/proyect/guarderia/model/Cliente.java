package com.proyect.guarderia.model;

import jakarta.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cliente_dk;
    private String identificacion_cliente;
    private String nombres_cliente;
    private String apellidos_cliente;
    private String direccion;
    private String correo;
    private long celular;
    
    public Cliente(int cliente_dk, String identificacion_cliente, String nombres_cliente, String apellidos_cliente,
            String direccion, String correo, long celular) {
        this.cliente_dk = cliente_dk;
        this.identificacion_cliente = identificacion_cliente;
        this.nombres_cliente = nombres_cliente;
        this.apellidos_cliente = apellidos_cliente;
        this.direccion = direccion;
        this.correo = correo;
        this.celular = celular;
    }

    public Cliente() {
    }

    public int getCliente_dk() {
        return cliente_dk;
    }

    public void setCliente_dk(int cliente_dk) {
        this.cliente_dk = cliente_dk;
    }

    public String getIdentificacion_cliente() {
        return identificacion_cliente;
    }

    public void setIdentificacion_cliente(String identificacion_cliente) {
        this.identificacion_cliente = identificacion_cliente;
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
