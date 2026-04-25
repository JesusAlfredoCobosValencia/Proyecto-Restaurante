/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.restaurantee;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author elcre
 */
public class Gerente_EmpleadosController implements Initializable {
    
    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> comboRol;

    @FXML
    private TextField txtPassword;
    
    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TableColumn<Usuario, Integer> colId;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colRol;

    @FXML
    private TableColumn<Usuario, String> colPassword;

    private Usuario usuarioSeleccionado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboRol.getItems().addAll("gerente", "mesero", "cajero", "recepcionista", "chef");
        colId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        cargarUsuarios();
        tablaUsuarios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, nuevoUsuario) -> {
            if(nuevoUsuario != null){
                usuarioSeleccionado = nuevoUsuario;
                txtNombre.setText(nuevoUsuario.getNombre());
                comboRol.setValue(nuevoUsuario.getRol());
                txtPassword.setText(nuevoUsuario.getPassword());
            }
        }); 
    }
    
    @FXML
    private void regresarGerente(ActionEvent event){
        MetodosGen.cambiarVista(event, "Administrador.fxml");
    }
    
    @FXML
    private void agregarUsuario(ActionEvent event){
        String nombre = txtNombre.getText();
        String rol = comboRol.getValue();
        String password = txtPassword.getText();
        if(nombre.isEmpty() || rol == null || password.isEmpty()){
            System.out.println("Completa todos los campos");
            return;
        }
        UsuarioDAO dao = new UsuarioDAO();
        dao.agregarUsuario(nombre, rol, password);
        limpiarCampos();
        cargarUsuarios();
        System.out.println("Usuario agregado");
    }
    
    @FXML
    private void modificarUsuario(ActionEvent event){
        if(usuarioSeleccionado == null){
            System.out.println("Selecciona un usuario en la tabla");
            return;
        }
        String nombre = txtNombre.getText();
        String rol = comboRol.getValue();
        String password = txtPassword.getText();
        if(nombre.isEmpty() || rol == null || password.isEmpty()){
            System.out.println("Completa todos los campos");
            return;
        }
        UsuarioDAO dao = new UsuarioDAO();
        dao.modificarUsuario(usuarioSeleccionado.getIdUsuario(), nombre, rol, password);
        limpiarCampos();
        cargarUsuarios();
        System.out.println("Usuario modificado");
    }
    
    @FXML
    private void eliminarUsuario(ActionEvent event){
        if(usuarioSeleccionado == null){
            System.out.println("Selecciona un usuario en la tabla");
            return;
        }
        UsuarioDAO dao = new UsuarioDAO();
        dao.eliminarUsuario(usuarioSeleccionado.getIdUsuario());
        limpiarCampos();
        cargarUsuarios();
        System.out.println("Usuario eliminado");
    }
    
    public void limpiarCampos(){
        txtNombre.clear();
        txtPassword.clear();
        comboRol.setValue(null);
        usuarioSeleccionado = null;
        tablaUsuarios.getSelectionModel().clearSelection();
    }
    
    public void cargarUsuarios(){
        UsuarioDAO dao = new UsuarioDAO();
        ArrayList<Usuario> lista = dao.verUsuarios();
        ObservableList<Usuario> datos = FXCollections.observableArrayList(lista);
        tablaUsuarios.setItems(datos);
    }
    
    
}