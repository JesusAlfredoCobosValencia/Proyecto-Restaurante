/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author venta
 */
public class UsuarioDAO {
    public ArrayList<Usuario> verUsuarios(){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        try{
            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "select * from usuario";
            Statement preguntas = conexionSQL.createStatement();
            ResultSet respuesta = preguntas.executeQuery(consultaSQL);
            while(respuesta.next()){
                Usuario usuario = new Usuario(
                    respuesta.getInt("id_usuario"),
                    respuesta.getString("username"),
                    respuesta.getString("rol"),
                    respuesta.getString("password"));
                listaUsuarios.add(usuario);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listaUsuarios;
    }
    
    public void agregarUsuario(String nombre, String rol, String password){
        try{
            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "insert into usuario(username, rol, password) values ('"+ nombre + "', '" + rol + "', '" + password + "')";
            Statement preguntas = conexionSQL.createStatement();
            preguntas.executeUpdate(consultaSQL);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void modificarUsuario(int idUsuario, String nombre, String rol, String password){
        try{
            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "update usuario set username = '" + nombre + "', rol = '" + rol + "', password = '" + password + "' where id_usuario = " + idUsuario;
            Statement preguntas = conexionSQL.createStatement();
            preguntas.executeUpdate(consultaSQL);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void eliminarUsuario(int idUsuario){
        try{
            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "delete from usuario where id_usuario = " + idUsuario;
            Statement preguntas = conexionSQL.createStatement();
            preguntas.executeUpdate(consultaSQL);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}