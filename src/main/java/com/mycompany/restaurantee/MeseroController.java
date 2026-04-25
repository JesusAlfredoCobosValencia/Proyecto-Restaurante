/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.restaurantee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;



import java.util.*;



/**
 * FXML Controller class
 *
 * @author venta
 */






public class MeseroController implements Initializable {

    ArrayList<String> productos = new ArrayList<>();
    ArrayList<Integer> cantidades = new ArrayList<>();
    @FXML private TableView<Producto> tablaHamburguesas;
    @FXML private TableView<Producto> tablaPapas;
    @FXML private TableView<Producto> tablaBebidas;

    @FXML private TableView<DetallePedido> tablaPedido;

    @FXML private TextField txtCantidadH;
    @FXML private TextField txtCantidadP;
    @FXML private TextField txtCantidadB;
    @FXML private TextField txtMesa;
    
    @FXML private TableColumn<Producto, String> colHamburguesas;
    @FXML private TableColumn<Producto, String> colPapas;
    @FXML private TableColumn<Producto, String> colBebidas;
    
    @FXML private TableColumn<DetallePedido, String> colNombrePedido;
    @FXML private TableColumn<DetallePedido, Integer> colCantidadPedido;
    @FXML private TableColumn<DetallePedido, Double> colPrecioPedido;
    
    


    int idMesaSeleccionada;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProductoDAO dao = new ProductoDAO();

        // CARGAR PRODUCTOS
        tablaHamburguesas.getItems().addAll(dao.obtenerPorCategoria("Hamburguesas"));
        tablaPapas.getItems().addAll(dao.obtenerPorCategoria("Papas"));
        tablaBebidas.getItems().addAll(dao.obtenerPorCategoria("Bebidas"));

        // COLUMNAS PRODUCTOS (solo nombre)
        colHamburguesas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPapas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colBebidas.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        // COLUMNAS PEDIDO
        colNombrePedido.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCantidadPedido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioPedido.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // 🔥 SOLUCIÓN AL PROBLEMA (IMPORTANTE)
        tablaHamburguesas.setOnMouseClicked(e -> {
            tablaPapas.getSelectionModel().clearSelection();
            tablaBebidas.getSelectionModel().clearSelection();
        });

        tablaPapas.setOnMouseClicked(e -> {
            tablaHamburguesas.getSelectionModel().clearSelection();
            tablaBebidas.getSelectionModel().clearSelection();
        });

        tablaBebidas.setOnMouseClicked(e -> {
            tablaHamburguesas.getSelectionModel().clearSelection();
            tablaPapas.getSelectionModel().clearSelection();
        });
    }    
    
     @FXML
    private void btnRegresar(ActionEvent event) {
        MetodosGen.cambiarVista(event, "Login.fxml");
    }
    
    
    public void agregarProducto(String prod, int cant){

    productos.add(prod);
    cantidades.add(cant);

    System.out.println("Agregado: " + prod);
}
    
    
    
    
    
    public void confirmarPedido(){
        
        
    if(txtMesa.getText().isEmpty()){
        System.out.println("Ingresa mesa");
        return;
    }

    idMesaSeleccionada = Integer.parseInt(txtMesa.getText());

    if(productos.isEmpty()){
        System.out.println("Debe agregar al menos un producto");
        return;
    }

    PedidoDAO dao = new PedidoDAO();

    int idPedido = dao.crearPedido(idMesaSeleccionada);

    for(int i=0; i<productos.size(); i++){
        dao.agregarDetalle(idPedido, productos.get(i), cantidades.get(i));
    }

    productos.clear();
    cantidades.clear();
    tablaPedido.getItems().clear();
    txtMesa.clear();

    System.out.println("Pedido registrado");
}
    
    
    
    
    @FXML
    public void btnAgregar(){

    Producto seleccionado = null;
    int cantidad = 0;

    // HAMBURGUESAS
    if(tablaHamburguesas.getSelectionModel().getSelectedItem() != null){
        seleccionado = tablaHamburguesas.getSelectionModel().getSelectedItem();

        if(txtCantidadH.getText().isEmpty()){
            System.out.println("Ingresa cantidad hamburguesa");
            return;
        }

        cantidad = Integer.parseInt(txtCantidadH.getText());
    }

    // PAPAS
    else if(tablaPapas.getSelectionModel().getSelectedItem() != null){
        seleccionado = tablaPapas.getSelectionModel().getSelectedItem();

        if(txtCantidadP.getText().isEmpty()){
            System.out.println("Ingresa cantidad papas");
            return;
        }

        cantidad = Integer.parseInt(txtCantidadP.getText());
    }

    // BEBIDAS
    else if(tablaBebidas.getSelectionModel().getSelectedItem() != null){
        seleccionado = tablaBebidas.getSelectionModel().getSelectedItem();

        if(txtCantidadB.getText().isEmpty()){
            System.out.println("Ingresa cantidad bebida");
            return;
        }

        cantidad = Integer.parseInt(txtCantidadB.getText());
    }

    if(seleccionado == null){
        System.out.println("Selecciona un producto");
        return;
    }

    if(cantidad <= 0){
        System.out.println("Cantidad inválida");
        return;
    }

    
    agregarProducto(seleccionado.getNombre(), cantidad);

    actualizarTablaPedido();

   
    txtCantidadH.clear();
    txtCantidadP.clear();
    txtCantidadB.clear();
}
    
    
    public void actualizarTablaPedido(){

    ObservableList<DetallePedido> lista = FXCollections.observableArrayList();

    ProductoDAO dao = new ProductoDAO();

    for(int i=0; i<productos.size(); i++){

        String nombre = productos.get(i);
        int cantidad = cantidades.get(i);

        double precio = dao.obtenerPrecio(nombre);

        lista.add(new DetallePedido(nombre, cantidad, precio));
    }

    tablaPedido.setItems(lista);
}
    
    
    
    
 
    
}
