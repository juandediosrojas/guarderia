package com.proyect.guarderia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="registros")
public class Registro {

    @Id
    private int dk;
    private int mascota_fk;
    private int cliente_fk;
    private LocalDateTime fecha_entrada;
    private LocalDateTime fecha_salida;

    public Registro() {
    }

    public Registro(int dk, int mascota_fk, int cliente_fk, LocalDateTime fecha_entrada, LocalDateTime fecha_salida) {
        this.dk = dk;
        this.mascota_fk = mascota_fk;
        this.cliente_fk = cliente_fk;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
    }

    public int getDk() {
        return dk;
    }

    public void setDk(int dk) {
        this.dk = dk;
    }

    public int getMascota_fk() {
        return mascota_fk;
    }

    public void setMascota_fk(int mascota_fk) {
        this.mascota_fk = mascota_fk;
    }

    public int getCliente_fk() {
        return cliente_fk;
    }

    public void setCliente_fk(int cliente_fk) {
        this.cliente_fk = cliente_fk;
    }

    public LocalDateTime getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(LocalDateTime fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public LocalDateTime getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDateTime fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    
}
