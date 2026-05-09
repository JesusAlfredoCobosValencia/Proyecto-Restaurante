package com.mycompany.restaurantee;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InventarioDAO {

    private final int MINIMO_FIJO = 10;

    public ArrayList<Inventario> verInventario() {
        ArrayList<Inventario> lista = new ArrayList<>();
        try {
            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "SELECT * FROM inventario";
            Statement preguntas = conexionSQL.createStatement();
            ResultSet respuesta = preguntas.executeQuery(consultaSQL);
            while (respuesta.next()) {
                int unidades = respuesta.getInt("unidades");
                String estado = unidades >= MINIMO_FIJO ? "Suficiente" : "Bajo";
                Inventario inv = new Inventario(
                    respuesta.getString("ingrediente"),
                    unidades,
                    MINIMO_FIJO,
                    estado
                );
                lista.add(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void agregarIngrediente(String ingrediente, int unidades){
        try{
            Connection conexion = Conexion.getConnection();
            String consultaBuscar = "SELECT unidades FROM inventario WHERE ingrediente = '" + ingrediente + "'";
            Statement stBuscar = conexion.createStatement();
            ResultSet rs = stBuscar.executeQuery(consultaBuscar);

            if(rs.next()){
                int unidadesActuales = rs.getInt("unidades");
                modificarIngrediente(ingrediente, unidadesActuales + unidades);
            }else{
                String estado = unidades >= MINIMO_FIJO ? "Suficiente" : "Bajo";
                String sql = "INSERT INTO inventario (ingrediente, unidades, estado) "
                           + "VALUES ('" + ingrediente + "', " + unidades + ", '" + estado + "')";
                Statement st = conexion.createStatement();
                st.executeUpdate(sql);
                registrarMovimiento(ingrediente, "Agregar", unidades);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void modificarIngrediente(String ingrediente, int nuevasUnidades) {
        try {
            Connection conexionSQL = Conexion.getConnection();

            String consultaAnterior = "SELECT unidades FROM inventario WHERE ingrediente = '" + ingrediente + "'";
            Statement preguntasAnterior = conexionSQL.createStatement();
            ResultSet respuestaAnterior = preguntasAnterior.executeQuery(consultaAnterior);

            int unidadesAnteriores = 0;
            if(respuestaAnterior.next()){
                unidadesAnteriores = respuestaAnterior.getInt("unidades");
            }

            String estado = nuevasUnidades >= MINIMO_FIJO ? "Suficiente" : "Bajo";
            String consultaSQL = "UPDATE inventario SET unidades = " + nuevasUnidades
                               + ", estado = '" + estado + "' WHERE ingrediente = '" + ingrediente + "'";
            Statement preguntas = conexionSQL.createStatement();
            preguntas.executeUpdate(consultaSQL);

            int diferencia = nuevasUnidades - unidadesAnteriores;
            if(diferencia != 0){
                String accion = diferencia > 0 ? "Agregar" : "Descuento";
                registrarMovimiento(ingrediente, accion, Math.abs(diferencia));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarIngrediente(String ingrediente, int unidadesActuales) {
        try {
            Connection conexionSQL = Conexion.getConnection();

            registrarMovimiento(ingrediente, "Descuento", unidadesActuales);

            String consultaSQL = "DELETE FROM inventario WHERE ingrediente = '" + ingrediente + "'";
            Statement preguntas = conexionSQL.createStatement();
            preguntas.executeUpdate(consultaSQL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int obtenerIdInventario(String ingrediente){
        int idInventario = -1;
        try{
            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "SELECT id_inventario FROM inventario WHERE ingrediente = '" + ingrediente + "'";
            Statement preguntas = conexionSQL.createStatement();
            ResultSet respuesta = preguntas.executeQuery(consultaSQL);
            if(respuesta.next()){
                idInventario = respuesta.getInt("id_inventario");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return idInventario;
    }

    private void registrarMovimiento(String ingrediente, String accion, int cantidad) {
        try {
            int idInventario = obtenerIdInventario(ingrediente);
            if(idInventario == -1){
                return;
            }

            Connection conexionSQL = Conexion.getConnection();
            String consultaSQL = "INSERT INTO movimientos (id_inventario, ingrediente, accion, cantidad) "
                               + "VALUES (" + idInventario + ", '" + ingrediente + "', '" + accion + "', " + cantidad + ")";
            Statement preguntas = conexionSQL.createStatement();
            preguntas.executeUpdate(consultaSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Movimientos> verMovimientos(){
        ArrayList<Movimientos> listaMov = new ArrayList<>();
        try{
            Connection conexion = Conexion.getConnection();
            String sql = "SELECT * FROM movimientos ORDER BY fecha DESC";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Movimientos mov = new Movimientos(
                    rs.getString("ingrediente"),
                    rs.getString("accion"),
                    rs.getInt("cantidad"),
                    rs.getString("fecha")
                );
                listaMov.add(mov);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaMov;
    }
}