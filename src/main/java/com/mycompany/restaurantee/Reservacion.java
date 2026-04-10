/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package com.mycompany.restaurantee;

/**
 *
 * @author venta
 */


import java.time.LocalDate;

public class Reservacion {
    private int idReservacion;
    private String nombreReservacion;
    private int numPersonas;
    private LocalDate fecha;
    private String estado;
    
    
    
    public int getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(int idReservacion) {
        this.idReservacion = idReservacion;
    }

    public String getNombreReservacion() {
        return nombreReservacion;
    }

    public void setNombreReservacion(String nombreReservacion) {
        this.nombreReservacion = nombreReservacion;
    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    
}
