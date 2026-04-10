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

import javafx.scene.shape.Circle;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import javafx.animation.Timeline;

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
    @FXML private TextFlow areaReservaciones;
    
    @FXML private TextField txtNombre;
    @FXML private TextField numMesa;
    @FXML private TextField txtFecha;
    @FXML private TextField txtHora;
    
    
    
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarMesas();
        cargarReservaciones();
         
        Timeline  tiempo = new Timeline (
         new KeyFrame(Duration.seconds(10), e ->{
             
         limpiarReservaciones();
         cargarMesas();
         cargarReservaciones();
             
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
    
    
    
    
    public void cargarReservaciones(){
        try{
            Connection conexionSQL = Conexion.getConnection();
        
        String consultaSQL = "select * from  reservacion";
        
        Statement preguntas = conexionSQL.createStatement();
        ResultSet respuesta = preguntas.executeQuery(consultaSQL);
            
       
        
        areaReservaciones.getChildren().clear();    
        
        while(respuesta.next()){
            String linea = respuesta.getString("nombre_cliente") + " - mesa " + respuesta.getInt("id_mesa") + " - " + respuesta.getString("fecha_hora") +"\n";
            
              Text texto = new Text(linea);
              areaReservaciones.getChildren().add(texto);
        }
        
       
        
        }
        catch(Exception e){
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
    
    
    
    
    public void btnreservar(ActionEvent event){
        
        String nombre = txtNombre.getText();
        String mesatxt = numMesa.getText();
        
        
        if(nombre.isEmpty() || mesatxt.isEmpty()){
                
            System.out.println("Escribe el nombre o numero de mesa");
            return;
        }
        
        ReservacionDAO reservaciondao = new ReservacionDAO();
        MesaDAO mesadao = new MesaDAO();
        
        int numMesa = Integer.parseInt(mesatxt);
        String estado = mesadao.obtenerEstado(numMesa);
        
        if(estado.equals("libre")){
            
            
            
             LocalDateTime fechaHora = LocalDateTime.now();
             
             
             
             reservaciondao.insertarReservacion(nombre, numMesa, fechaHora);
             mesadao.cambiarEstado(numMesa, "reservada");
             
             cargarMesas();
             cargarReservaciones();   
        }
        else{
            System.out.println("mesa no disponible");
        }
    }
    
    
    
    public void btnLiberar(ActionEvent event){
       
        String mesatxt = numMesa.getText();
        
        
        if(mesatxt.isEmpty()){
                
            System.out.println("Escribe el numero de mesa");
            return;
        }
        
        MesaDAO mesadao = new MesaDAO();
        
        int numMesa = Integer.parseInt(mesatxt);
        mesadao.liberarMesa(numMesa);
        
        
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
    
    
    
    public void limpiarReservaciones(){
        try{
            
            String consultaSQL = "delete from reservacion where timestampdiff (minute, fecha_hora, now()) >= 15";
            
            Connection conexion = Conexion.getConnection();
            Statement preguntas = conexion.createStatement();
            preguntas.executeUpdate(consultaSQL);
            
            String consultaSQLliberar = "update  mesa set estado = 'libre' where numero not in (select id_mesa from reservacion)";
            preguntas.executeUpdate(consultaSQLliberar);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
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
    

