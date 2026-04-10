/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.restaurantee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 * FXML Controller class
 *
 * @author venta
 */
public class LoginController {
  
    @FXML private TextField datosUsuario;
    @FXML private TextField datosPassword;
    @FXML private Label mensajesFX;
    
    
    
    
    @FXML
    private void btnAcceso(ActionEvent event) {
        String datosUsuario = this.datosUsuario.getText();
        String datosPassword = this.datosPassword.getText();
        
        
        if(datosUsuario.isEmpty() || datosPassword.isEmpty()){
             mensajesFX.setText("Campos vacios");
             return;
        }
        
       try {
           Connection conexionSQL = Conexion.getConnection();
           
          String consultaSQL = "Select * from usuario where username = '" + datosUsuario + "' and password = '" + datosPassword + "'";
          
          Statement preguntas = conexionSQL.createStatement();
          ResultSet respuesta = preguntas.executeQuery(consultaSQL);
          
          
          if( respuesta.next()){
              
              String rol = respuesta.getString("rol");
              
              if( rol.equals("gerente")){
                  MetodosGen.cambiarVista(event, "Administrador.fxml");
              }
              
              if ( rol.equals("mesero")){
                  MetodosGen.cambiarVista(event, "Mesero.fxml");
              }
              
              if ( rol.equals("cajero")){
                  MetodosGen.cambiarVista(event, "Cajero.fxml");
              }
              
              if( rol.equals("recepcionista")){
                  MetodosGen.cambiarVista(event, "Recepcionista.fxml");
              }
              
              if( rol.equals("chef")){
                  MetodosGen.cambiarVista(event, "Chef.fxml");
              }
        
          }
          else{
            System.out.println("DATOS INCORRECTOS");
            mensajesFX.setText("Credenciales Incorrectas");
              } 
       
       
       }catch  (Exception e){
           e.printStackTrace();
           mensajesFX.setText("Error en la conexion con la base de datos");
           
       }
        
        
        
    
    }
    
    
    
    
    
    
    
    
}
