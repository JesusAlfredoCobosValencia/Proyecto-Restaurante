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
import java.util.*;

/**
 *
 * @author venta
 */
public class ProductoDAO {
    
    
    public void insertarProducto(Producto producto){
    try{
        Connection conexionSQL = Conexion.getConnection();
        String consultaSQL = "INSERT INTO producto(username, precio) VALUES ('" 
                    + producto.getNombre() + "', " + producto.getPrecio() + ")";
        
        Statement respuesta = conexionSQL.createStatement();
        respuesta.executeUpdate(consultaSQL);
        
    }
    catch(Exception e){
        e.printStackTrace();
    }
}
    
    
    
    public ArrayList<Producto> obtenerPorCategoria(String categoria){

    ArrayList<Producto> lista = new ArrayList<>();

    try{
        Connection conexionSQL = Conexion.getConnection();

        String consultaSQL = "SELECT * FROM producto WHERE categoria = '" + categoria + "'";

        Statement preguntas = conexionSQL.createStatement();
        ResultSet respuesta = preguntas.executeQuery(consultaSQL);

        while(respuesta.next()){

            Producto p = new Producto();

            p.setIdProducto(respuesta.getInt("id_producto"));
            p.setNombre(respuesta.getString("nombre"));
            p.setPrecio(respuesta.getDouble("precio"));

            lista.add(p);
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    return lista;
}
    
    
    
    
    
    public double obtenerPrecio(String nombre){

    double precio = 0;

    try{
        Connection conexion = Conexion.getConnection();
        String sql = "SELECT precio FROM producto WHERE nombre = '" + nombre + "'";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if(rs.next()){
            precio = rs.getDouble("precio");
        }

    }catch(Exception e){
        e.printStackTrace();
    }

    return precio;
}
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
