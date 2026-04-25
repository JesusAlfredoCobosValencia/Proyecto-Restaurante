/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.restaurantee;

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.shape.Circle;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author venta
 */
public class RecepcionistaController implements Initializable {

    @FXML private Circle mesa1;
    @FXML private Circle mesa2;
    @FXML private Circle mesa3;
    @FXML private Circle mesa4;
    @FXML private Circle mesa5;
    @FXML private Circle mesa6;   
    
    @FXML private TextField txtNombre;
    @FXML private TextField numMesa;
    @FXML private TextField txtFecha;
    @FXML private TextField txtHora;
    @FXML
    private TableView<ListaEspera> tablaEspera;

    @FXML
    private TableColumn<ListaEspera, Integer> colIdEspera;
    @FXML
    private TableColumn<ListaEspera, String> colNombreEspera;
    @FXML
    private TableColumn<ListaEspera, Integer> colMesaEspera;
    @FXML
    private TableView<Reservacion> tablaReservaciones;

    @FXML
    private TableColumn<Reservacion, String> colNombre;
    @FXML
    private TableColumn<Reservacion, Integer> colMesa;
    @FXML
    private TableColumn<Reservacion, LocalDate> colFecha;
    @FXML
    private TableColumn<Reservacion, LocalTime> colHora;
    
    
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Lista reservaciones
         colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
         colMesa.setCellValueFactory(new PropertyValueFactory<>("mesa"));
         colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
         colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
         
         colHora.setCellFactory(column -> new TableCell<Reservacion, LocalTime>() {
             @Override
             protected void updateItem(LocalTime item, boolean empty) {
                 super.updateItem(item, empty);
                 if (empty || item == null) {
                     setText(null);
                 } else {
                     setText(item.withSecond(0).withNano(0).toString());
                 }
             }
         });

         cargarReservaciones();
        //Lista espera
         colIdEspera.setCellValueFactory(new PropertyValueFactory<>("id"));
         colNombreEspera.setCellValueFactory(new PropertyValueFactory<>("nombre"));
         colMesaEspera.setCellValueFactory(new PropertyValueFactory<>("mesa"));
        
        cargarMesas();
        
         
        Timeline  tiempo = new Timeline (
         new KeyFrame(Duration.seconds(900), e ->{
             
         cargarListaEspera();
         limpiarReservaciones();
         cargarMesas();
             
         })
        );
        
        tiempo.setCycleCount(Timeline.INDEFINITE);
        tiempo.play();
       
      
    }
    
    
    
    
    @FXML
    private void btnRegresar(ActionEvent event) {
        MetodosGen.cambiarVista(event, "Login.fxml");
        
    }
    
    
    public void cambiarColor(Circle mesa, String estado){
        
        if(estado.equals("libre")){
            mesa.setStyle("-fx-fill: #62e40b;");
        }
        
        if(estado.equals("reservada")){
            mesa.setStyle("-fx-fill: #fff822;");
        }
        
        if(estado.equals("ocupada")){
            mesa.setStyle("-fx-fill: #e3290b;");
        }  
    }
    
    
    public void cargarMesas(){
        try{
        Connection conexionSQL = Conexion.getConnection();
        
        String consultaSQL = "select * from  mesa";
        
        Statement preguntas = conexionSQL.createStatement();
        ResultSet respuesta = preguntas.executeQuery(consultaSQL);
        
        while(respuesta.next()){
            
            int numero = respuesta.getInt("numero");
            String estado = respuesta.getString("estado");
            
            if(numero == 1){
                cambiarColor(mesa1, estado);
            }
            
            if(numero == 2){
                cambiarColor(mesa2, estado);
            }
            
            if(numero == 3){
                cambiarColor(mesa3, estado);
            }
            
            if(numero == 4){
                cambiarColor(mesa4, estado);
            }
            
            if(numero == 5){
                cambiarColor(mesa5, estado);
            }
            
            if(numero == 6){
                cambiarColor(mesa6, estado);
            }
        }
        }
        catch( Exception e){
                e.printStackTrace( );
                }
            
            
        }
    

    
    
    
    public void cargarReservaciones() {

        ObservableList<Reservacion> lista = FXCollections.observableArrayList();

        try {
            Connection conexion = Conexion.getConnection();
            String sql = "SELECT * FROM reservacion";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                String nombre = rs.getString("nombre_cliente");
                int mesa = rs.getInt("id_mesa");

                Timestamp ts = rs.getTimestamp("fecha_hora");
                LocalDateTime ldt = ts.toLocalDateTime();

                lista.add(new Reservacion(
                        nombre,
                        mesa,
                        ldt.toLocalDate(),
                        ldt.toLocalTime()
                ));
            }

            tablaReservaciones.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public void liberarMesa(int idMesa){

        MesaDAO dao = new MesaDAO();
        dao.liberarMesa(idMesa);

        cargarMesas();
    }
    
    
    
    public void ocuparMesa(int idMesa){

        MesaDAO dao = new MesaDAO();
        dao.ocuparMesa(idMesa); 

        cargarMesas();
    }
    public void cargarListaEspera() {

        ObservableList<ListaEspera> lista = FXCollections.observableArrayList();

        try {
            Connection conexion = Conexion.getConnection();

            String sql = "SELECT * FROM lista_espera";
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(new ListaEspera(
                        rs.getInt("id_espera"),
                        rs.getString("nombre_cliente"),
                        rs.getInt("id_mesa")
                ));
            }

            tablaEspera.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public void btnreservar(ActionEvent event) {

        String nombre = txtNombre.getText();
        String mesatxt = numMesa.getText();
        String fechaTexto = txtFecha.getText();
        String horaTexto = txtHora.getText();

        if (nombre.isEmpty() || mesatxt.isEmpty() || fechaTexto.isEmpty() || horaTexto.isEmpty()) {
            System.out.println("Complete todos los campos requeridos");
            return;
        }

        int mesa = Integer.parseInt(mesatxt);

        LocalDate fecha;
        LocalTime hora;

        try {
            fecha = LocalDate.parse(fechaTexto);
            hora = LocalTime.parse(horaTexto);
        } catch (Exception e) {
            System.out.println("Formato incorrecto. Usa yyyy-MM-dd y HH:mm");
            return;
        }

        LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

        ReservacionDAO reservacionDAO = new ReservacionDAO();
        MesaDAO mesaDAO = new MesaDAO();
        ListaEsperaDAO esperaDAO = new ListaEsperaDAO();

        String estadoMesa = mesaDAO.obtenerEstado(mesa);

      
        if (!estadoMesa.equals("libre")) {

            esperaDAO.insertarEnEspera(nombre, mesa);
            cargarListaEspera();

            System.out.println("Mesa no disponible. Agregado a lista de espera");
            return;
        }

        reservacionDAO.insertarReservacion(nombre, mesa, fechaHora);
        mesaDAO.cambiarEstado(mesa, "reservada");

        cargarReservaciones();
        cargarMesas();

        System.out.println("Reservación creada");
    }
    public void asignarDesdeListaEspera(int idMesa) {

        ListaEsperaDAO esperaDAO = new ListaEsperaDAO();
        ReservacionDAO reservacionDAO = new ReservacionDAO();
        MesaDAO mesaDAO = new MesaDAO();

        ListaEspera cliente = esperaDAO.obtenerPrimero();

        if (cliente != null) {

            LocalDateTime fechaHora = LocalDateTime.now();

            reservacionDAO.insertarReservacion(
                    cliente.getNombre(),
                    idMesa,
                    fechaHora
            );

            esperaDAO.eliminar(cliente.getId());

            mesaDAO.cambiarEstado(idMesa, "reservada");

            cargarListaEspera();
            cargarReservaciones();
            cargarMesas();
        }
    }
    
    
    
    public void btnLiberar(ActionEvent event) {

        String mesatxt = numMesa.getText();

        if (mesatxt.isEmpty()) {
            System.out.println("Escribe el numero de mesa");
            return;
        }

        int numMesa = Integer.parseInt(mesatxt);

        MesaDAO mesadao = new MesaDAO();

        
        mesadao.liberarMesa(numMesa);

        
        asignarDesdeListaEspera(numMesa);

        cargarMesas();
    }
    
    
    
    
    public void btnOcupar(ActionEvent event){
        
       
        String mesatxt = numMesa.getText();
        
        
        if(mesatxt.isEmpty()){
                
            System.out.println("Escribe elnumero de mesa");
            return;
        }
        
       
        MesaDAO mesadao = new MesaDAO();
        
        int numMesa = Integer.parseInt(mesatxt);
        mesadao.ocuparMesa(numMesa);
        
        cargarMesas();
        
    }
    
    
    
    public void limpiarReservaciones() {
        try {

            Connection conexion = Conexion.getConnection();
            Statement st = conexion.createStatement();

            String eliminar = "DELETE FROM reservacion WHERE TIMESTAMPDIFF(MINUTE, fecha_hora, NOW()) >= 15";
            st.executeUpdate(eliminar);

            String liberar = "UPDATE mesa SET estado = 'libre' WHERE numero NOT IN (SELECT id_mesa FROM reservacion)";
            st.executeUpdate(liberar);

            String sqlMesasLibres = "SELECT numero FROM mesa WHERE estado = 'libre'";
            ResultSet rs = st.executeQuery(sqlMesasLibres);

            while (rs.next()) {
                int mesaLibre = rs.getInt("numero");
                asignarDesdeListaEspera(mesaLibre);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String estadoRestaurante() {

        try {
            Connection conexion = Conexion.getConnection();
            String sql = "SELECT estado FROM mesa";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            boolean hayLibre = false;
            boolean todasOcupadas = true;

            while (rs.next()) {
                String estado = rs.getString("estado");

                if (estado.equals("libre")) {
                    hayLibre = true;
                    todasOcupadas = false;
                }

                if (estado.equals("reservada")) {
                    todasOcupadas = false;
                }
            }

            if (hayLibre) {
                return "libre";
            }

            if (todasOcupadas) {
                return "ocupado";
            }

            return "reservado"; // no hay libres pero hay reservadas

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
    
    public boolean lleno(){
        
     try{
            Connection conexionSQL = Conexion.getConnection();
        
        String consultaSQL = "select * from  mesa";
        
        Statement preguntas = conexionSQL.createStatement();
        ResultSet respuesta = preguntas.executeQuery(consultaSQL);
        
        boolean lleno = true;
        
        while(respuesta.next()){
            String estado = respuesta.getString("estado");
            
            if(estado.equals("libre")){
                lleno = false;
                return lleno;   
            }
            
        }
        if(lleno == true){
            System.out.println(" el restaurante esta lleno");
            return lleno;
        }
         
     }
     catch (Exception e ){
         e.printStackTrace();
     }
     return false;
     
        
 
    }
    
    
    
    
    
   
    
    
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    }
    

