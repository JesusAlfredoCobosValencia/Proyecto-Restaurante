/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author venta
 */
public class MesaDAO {
   
   public void verMesas(){
    try{
    Connection conexionSQL = Conexion.getConnection();
        
        String consultaSQL = "select * from  mesa";
        
        Statement preguntas = conexionSQL.createStatement();
        ResultSet respuesta = preguntas.executeQuery(consultaSQL);
        
        while (respuesta.next()){
            System.out.println("Mesa: " + respuesta.getInt("numero") + " Estado: " + respuesta.getString("estado"));
            
            
        }

    }
    catch(Exception e){
    e.printStackTrace();
    }
    
   }
   
    
    
    public void liberarMesa(int idMesa){
        try{
            Connection conexion = Conexion.getConnection();
            
             String consultaSQL = "update mesa set estado = 'libre' where id_mesa =" + idMesa;
            
            Statement preguntas = conexion.createStatement();
            preguntas.executeUpdate(consultaSQL);
            
        }
        catch(Exception e){
            e.printStackTrace( );
        }
    }
    
    
    public void ocuparMesa(int idMesa){
        try{
            Connection conexion = Conexion.getConnection();
            
             String consultaSQL = "update mesa set estado = 'ocupada' where id_mesa =" + idMesa;
            
            Statement preguntas = conexion.createStatement();
            preguntas.executeUpdate(consultaSQL);
            
           
            
        }
        catch(Exception e){
            e.printStackTrace( );
        }
    }
    
    
    
    public void cambiarEstado(int idMesa, String estado){
        try{
            
            Connection conexion = Conexion.getConnection();
            
             String consultaSQL = "update mesa set estado =   '"+ estado + "' where id_mesa =" + idMesa;
            
            Statement preguntas = conexion.createStatement();
            preguntas.executeUpdate(consultaSQL);
            
            
            
            
            
        }
        catch(Exception  e){
            e.printStackTrace();
        }
    }
    
    
    
    public String obtenerEstado(int idMesa){
        String estado = "";
        
        try{
            Connection conexion = Conexion.getConnection();
            String consultaSQL = "select estado from mesa where id_mesa =" + idMesa;
            
           
            Statement preguntas = conexion.createStatement();
            ResultSet respuesta = preguntas.executeQuery(consultaSQL);
            
            if(respuesta.next()){
                estado = respuesta.getString("estado");
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return estado;
    }
    
    
    
    
    
    
    
    
    
    
   
}
