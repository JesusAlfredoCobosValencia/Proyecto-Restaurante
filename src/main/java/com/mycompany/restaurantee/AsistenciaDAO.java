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
 * @author elcre
 */
public class AsistenciaDAO{
    public ArrayList<Asistencia> verAsistenciaPorFecha(String fecha){
        ArrayList<Asistencia> listaAsistencia = new ArrayList<>();
        try{
            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "select usuario.id_usuario, usuario.username, asistencia.estado "
                    + "from usuario left join asistencia "
                    + "on usuario.id_usuario = asistencia.id_usuario "
                    + "and asistencia.fecha = '" + fecha + "' "
                    + "order by usuario.id_usuario";
            Statement preguntas = conexionSQL.createStatement();
            ResultSet respuesta = preguntas.executeQuery(consultaSQL);
            while(respuesta.next()){
                String estado = respuesta.getString("estado");
                if(estado == null){
                    estado = "";
                }
                Asistencia asistencia = new Asistencia(
                    respuesta.getInt("id_usuario"),
                    respuesta.getString("username"),
                    fecha,
                    estado
                );
                listaAsistencia.add(asistencia);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listaAsistencia;
    }
    
    public void guardarAsistencia(int idUsuario, String fecha, String estado){
        try{
            Connection conexionSQL = Conexion.getConnection();
            String consultaBuscar = "select * from asistencia where id_usuario = " + idUsuario
                    + " and fecha = '" + fecha + "'";
            Statement preguntasBuscar = conexionSQL.createStatement();
            ResultSet respuestaBuscar = preguntasBuscar.executeQuery(consultaBuscar);
            if(respuestaBuscar.next()){
                String consultaSQL = "update asistencia set estado = '" + estado
                        + "' where id_usuario = " + idUsuario
                        + " and fecha = '" + fecha + "'";
                Statement preguntas = conexionSQL.createStatement();
                preguntas.executeUpdate(consultaSQL);
            }
            else{
                String consultaSQL = "insert into asistencia(id_usuario, fecha, estado) values ("
                        + idUsuario + ", '" + fecha + "', '" + estado + "')";
                Statement preguntas = conexionSQL.createStatement();
                preguntas.executeUpdate(consultaSQL);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
