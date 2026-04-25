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
import java.time.LocalTime;

public class Reservacion {
    private String nombre;
    private int mesa;
    private LocalDate fecha;
    private LocalTime hora;

    public Reservacion(String nombre, int mesa, LocalDate fecha, LocalTime hora) {
        this.nombre = nombre;
        this.mesa = mesa;
        this.fecha = fecha;
        this.hora = hora;
    }

    

    public Reservacion() {
    }

    
    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
   
    
}
