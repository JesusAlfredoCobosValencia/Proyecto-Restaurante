/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.restaurantee;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author venta
 */
public class AdministradorController implements Initializable{

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    
    @FXML
    private void btnRegresar(ActionEvent event){
        MetodosGen.cambiarVista(event, "Login.fxml");  
    }
    
    @FXML
    private void abrirVentanaEmpleados(ActionEvent event){
        MetodosGen.cambiarVista(event, "Gerente_Empleados.fxml");
    }
    
    @FXML
    private void abrirAlmacen(ActionEvent event){
        MetodosGen.cambiarVista(event, "Almacen.fxml");
    }
    
    @FXML
    private void abrirAsistencia(ActionEvent event){
        MetodosGen.cambiarVista(event, "Gerente_Asistencia.fxml");
    }
}