/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author venta
 */
public class PedidoDAO {
    
    
    

    public int crearPedido(int idMesa){

        int idPedido = 0;

        try{
            Connection conexionSQL = Conexion.getConnection();
            
            String consultaSQL = "INSERT INTO pedido (id_mesa, fecha) VALUES (" + idMesa + ", NOW())";
            Statement preguntas = conexionSQL.createStatement();
            
            preguntas.executeUpdate(consultaSQL, Statement.RETURN_GENERATED_KEYS);
           
            ResultSet respuesta = preguntas.getGeneratedKeys();


            if(respuesta.next()){
                idPedido = respuesta.getInt(1);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return idPedido;
    }
    
    

    
    
    
    public void agregarDetalle(int idPedido, String producto, int cantidad){

        try{
            Connection conexionSQL = Conexion.getConnection();
            Statement preguntas = conexionSQL.createStatement();

            String consultaSQL = "INSERT INTO detalle_pedido (id_pedido, producto, cantidad) VALUES ("
                    + idPedido + ", '" + producto + "', " + cantidad + ")";

            preguntas.executeUpdate(consultaSQL);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    
}
    
    
    
    
    
    

