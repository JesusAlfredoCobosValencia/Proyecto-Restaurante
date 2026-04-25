/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

/**
 *
 * @author Emanuel
 */
public class ListaEspera {
    private int id;
    private String nombre;
    private int mesa;

    public ListaEspera(int id, String nombre, int mesa){
        this.id = id;
        this.nombre = nombre;
        this.mesa = mesa;
    }

    public int getId(){ return id; }
    public String getNombre(){ return nombre; }
    public int getMesa(){ return mesa; }
    
}
