/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        String consultaSQL = "update mesa set estado = 'ocupada' where id_mesa = " + idMesa;

        Statement preguntas = conexion.createStatement();
        preguntas.executeUpdate(consultaSQL);

    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    
    
    
    public void cambiarEstado(int idMesa, String estado) {
    try {
        Connection conexion = Conexion.getConnection();
        
        // Asegúrate de que la consulta esté correctamente formada
        String consultaSQL = "UPDATE mesa SET estado = '" + estado + "' WHERE id_mesa = " + idMesa;
        
        Statement preguntas = conexion.createStatement();
        int rowsUpdated = preguntas.executeUpdate(consultaSQL);
        
        if (rowsUpdated > 0) {
            System.out.println("Estado de la mesa actualizado correctamente.");
        } else {
            System.out.println("No se actualizó el estado de la mesa.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    
    
    public String obtenerEstado(int idMesa){
    String estado = "";

    try{
        Connection conexion = Conexion.getConnection();

        String consultaSQL = "select estado from mesa where id_mesa = " + idMesa;

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
    
    
    
    
    
public boolean isMesaOcupada(int idMesa) {
    boolean mesaOcupada = false;

    try {
        // Obtener la conexión de la base de datos
        Connection conexionSQL = Conexion.getConnection();

        // Verificar si el estado de la mesa es 'reservada' o 'ocupada' en la tabla 'mesa'
        String consultaSQL = "SELECT estado FROM mesa WHERE id_mesa = " + idMesa;
        Statement pregunta = conexionSQL.createStatement();
        ResultSet resultado = pregunta.executeQuery(consultaSQL);

        // Si el estado es 'reservada' o 'ocupada', la mesa está ocupada
        if (resultado.next()) {
            String estado = resultado.getString("estado");
            if (estado.equals("reservada") || estado.equals("ocupada")) {
                mesaOcupada = true; // La mesa está reservada o ocupada
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return mesaOcupada;
}
    
    
    
    
    
    
    
    
    
  
    
    
    
    
    
    
    
    
    
    
   
}
