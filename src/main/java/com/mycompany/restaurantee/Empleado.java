/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

/**
 *
 * @author venta
 */
public class Empleado{
    private  int idEmpleado;
    private String nombre;
    private Usuario usuario;
    
    
    public Empleado(int idEmpleado , String nombre, Usuario usuario){
         
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.usuario = usuario;
    }
}
