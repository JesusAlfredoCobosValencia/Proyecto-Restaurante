/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantee;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;


/**
 *
 * @author venta
 */
public class MetodosGen {
    
public static void cambiarVista(ActionEvent evento, String vistaNueva){
       
   try{
           
    FXMLLoader loader = new FXMLLoader(MetodosGen.class.getResource(vistaNueva));
    Parent root = loader.load();
    
    
    Stage stage = (Stage) ((Node) evento.getSource() ).getScene().getWindow();
    stage.setScene(new Scene (root));
    stage.show();
    
            
  }catch (Exception e){
    e.printStackTrace();
    }
}
    
  
  
  
  
  
}
