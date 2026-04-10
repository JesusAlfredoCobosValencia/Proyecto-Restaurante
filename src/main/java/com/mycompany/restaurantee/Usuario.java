/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

/**
 *
 * @author venta
 */
public class Usuario {
 
    public int idUsuario;
    public String nombre;
    public String rol;
    public String password;
    
    public Usuario(int idUsuario , String nombre, String rol, String password){
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.rol = rol;
        this.password = password;
    }
    
    
    
    public void setIdUsuario( int id){
        this.idUsuario = id;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setRol(String rol){
        this.rol = rol;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    
    public int getIdUsuario(){
        return idUsuario;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getRol(){
        return rol;
    }
    
    public String getPassword(){
        return password;
    }
    
    
    
    
    
    
    
}
