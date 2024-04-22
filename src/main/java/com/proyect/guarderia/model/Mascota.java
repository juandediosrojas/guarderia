package com.proyect.guarderia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="mascotas")

public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dk;
    private int identifiacion;
    private String nombre;
    private String especie;
    private String raza;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    public Mascota(int dk, int identifiacion, String nombre, String especie,
            String raza, Cliente cliente) {
        this.dk = dk;
        this.identifiacion = identifiacion;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
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

    public int getIdentifiacion() {
        return identifiacion;
    }

    public void setIdentifiacion(int identifiacion_mascota) {
        this.identifiacion = identifiacion_mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre_mascota) {
        this.nombre = nombre_mascota;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie_mascota) {
        this.especie = especie_mascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza_mascota) {
        this.raza = raza_mascota;
    }

    public Cliente getCliente_fk() {
        return cliente;
    }

    public void setCliente_fk(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
