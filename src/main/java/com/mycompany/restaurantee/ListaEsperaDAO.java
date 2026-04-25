/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Emanuel
 */
public class ListaEsperaDAO {
    public void insertarEnEspera(String nombre, int idMesa) {
        try {
            Connection conexion = Conexion.getConnection();

            String sql = "INSERT INTO lista_espera (nombre_cliente, id_mesa) VALUES ('"
                    + nombre + "', " + idMesa + ")";

            Statement st = conexion.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ListaEspera obtenerPrimero() {
        try {
            Connection conexion = Conexion.getConnection();

            String sql = "SELECT * FROM lista_espera ORDER BY id_espera ASC LIMIT 1";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                return new ListaEspera(
                        rs.getInt("id_espera"),
                        rs.getString("nombre_cliente"),
                        rs.getInt("id_mesa")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    public void eliminar(int id) {
        try {
            Connection conexion = Conexion.getConnection();

            String sql = "DELETE FROM lista_espera WHERE id_espera = " + id;
            Statement st = conexion.createStatement();
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
