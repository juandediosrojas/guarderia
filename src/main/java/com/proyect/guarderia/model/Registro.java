package com.proyect.guarderia.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="registros")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dk;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mascota_fk")
    private Mascota mascota;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_fk")
    private Cliente cliente;

    private Date fechaEntrada;
    private Date fechaSalida;

    public Registro() {
    }

    public Registro(int dk, Mascota mascota, Cliente cliente, Date fechaEntrada, Date fechaSalida) {
        this.dk = dk;
        this.mascota = mascota;
        this.cliente = cliente;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public int getDk() {
        return dk;
    }

    public void setDk(int dk) {
        this.dk = dk;
    }

    public Mascota getMascota_fk() {
        return mascota;
    }

    public void setMascota_fk(Mascota mascota) {
        this.mascota = mascota;
    }

    public Cliente getCliente_fk() {
        return cliente;
    }

    public void setCliente_fk(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
