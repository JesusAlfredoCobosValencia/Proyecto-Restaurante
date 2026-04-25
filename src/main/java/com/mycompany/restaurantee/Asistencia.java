/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

/**
 *
 * @author elcre
 */
public class Asistencia{

    private int idUsuario;
    private String nombre;
    private String fecha;
    private String estado;

    public Asistencia(int idUsuario, String nombre, String fecha, String estado){
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getIdUsuario(){
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getFecha(){
        return fecha;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }
}