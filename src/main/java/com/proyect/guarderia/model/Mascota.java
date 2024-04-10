package com.proyect.guarderia.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="mascotas")

public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dk;
    private int identifiacion_mascota;
    private String nombre_mascota;
    private String especie_mascota;
    private String raza_mascota;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_fk")
    private Cliente cliente;

    public Mascota(int dk, int identifiacion_mascota, String nombre_mascota, String especie_mascota,
            String raza_mascota, Cliente cliente) {
        this.dk = dk;
        this.identifiacion_mascota = identifiacion_mascota;
        this.nombre_mascota = nombre_mascota;
        this.especie_mascota = especie_mascota;
        this.raza_mascota = raza_mascota;
        this.cliente = cliente;
    }

    public Mascota() {

    }

    public int getDk() {
        return dk;
    }

    public void setDk(int dk) {
        this.dk = dk;
    }

    public int getIdentifiacion_mascota() {
        return identifiacion_mascota;
    }

    public void setIdentifiacion_mascota(int identifiacion_mascota) {
        this.identifiacion_mascota = identifiacion_mascota;
    }

    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {
        this.nombre_mascota = nombre_mascota;
    }

    public String getEspecie_mascota() {
        return especie_mascota;
    }

    public void setEspecie_mascota(String especie_mascota) {
        this.especie_mascota = especie_mascota;
    }

    public String getRaza_mascota() {
        return raza_mascota;
    }

    public void setRaza_mascota(String raza_mascota) {
        this.raza_mascota = raza_mascota;
    }

    public Cliente getCliente_fk() {
        return cliente;
    }

    public void setCliente_fk(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
