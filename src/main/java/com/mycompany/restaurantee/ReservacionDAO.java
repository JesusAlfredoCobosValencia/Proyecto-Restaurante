/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

import java.sql.Connection;
import java.sql.Statement;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author venta
 */
public class ReservacionDAO {
    
    public void insertarReservacion (String nombre, int idMesa, LocalDateTime fechaHora){
        
        try{
            
            Connection conexion = Conexion.getConnection();
            
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = fechaHora.format(formato);
        
            String consultaSQL = "insert into reservacion (nombre_cliente, id_mesa, fecha_hora) values ('" + nombre + "' , " + idMesa + " , '" + fechaFormateada +"')";
            
            Statement preguntas = conexion.createStatement();
            preguntas.executeUpdate(consultaSQL);
            
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
}
