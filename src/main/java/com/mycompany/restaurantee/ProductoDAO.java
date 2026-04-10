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
public class ProductoDAO {
    
    
    public void insertarProducto(Producto producto){
    try{
        Connection conexionSQL = Conexion.getConnection();
        String consultaSQL = "INSERT INTO producto(nombre, precio) VALUES ('" 
                    + producto.getNombre() + "', " + producto.getPrecio() + ")";
        
        Statement respuesta = conexionSQL.createStatement();
        respuesta.executeUpdate(consultaSQL);
        
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
}
