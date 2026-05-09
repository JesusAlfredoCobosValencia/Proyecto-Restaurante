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


import javafx.stage.Stage;               // Para usar Stage (ventana)
import javafx.scene.Scene;               // Para crear una escena
import javafx.scene.layout.VBox;          // Para el layout VBox
import javafx.scene.control.Button;  

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
        //colNombrePedido.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        //colCantidadPedido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        //colPrecioPedido.setCellValueFactory(new PropertyValueFactory<>("precio"));

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
    
    
    
    
    
@FXML
private void confirmarPedido() {
    if (productos.isEmpty()) {
        System.out.println("No se ha agregado ningún producto.");
        return;
    }

    // Obtener el ID de la mesa seleccionada
    int idMesaSeleccionada = Integer.parseInt(txtMesa.getText());

    // Crear el pedido en la base de datos
    PedidoDAO pedidoDao = new PedidoDAO();
    int idPedido = pedidoDao.crearPedido(idMesaSeleccionada);
    
    // Agregar los detalles del pedido
    for (int i = 0; i < productos.size(); i++) {
        String producto = productos.get(i);
        int cantidad = cantidades.get(i);
        pedidoDao.agregarDetalle(idPedido, producto, cantidad);
    }

    // Cambiar el estado de la mesa a "ocupada"
    MesaDAO mesaDao = new MesaDAO();
    mesaDao.cambiarEstado(idMesaSeleccionada, "ocupada");
    
    // Limpiar la lista de productos y cantidades
    productos.clear();
    cantidades.clear();
    tablaPedido.getItems().clear();
    txtMesa.clear();

    // Confirmación de éxito
    System.out.println("Pedido confirmado correctamente.");
}





    
    
@FXML
public void btnAgregar() {
    if (txtMesa.getText().isEmpty()) {
        System.out.println("Por favor, asigna una mesa antes de agregar productos.");
        return;  // Detener el proceso si no hay mesa asignada
    }

    // Convertir el valor de la mesa
    idMesaSeleccionada = Integer.parseInt(txtMesa.getText());

    // Crear una instancia de MesaDAO para verificar la disponibilidad de la mesa
    MesaDAO mesaDao = new MesaDAO();

    // Verificar si la mesa está ocupada o reservada
    if (mesaDao.isMesaOcupada(idMesaSeleccionada)) {
        System.out.println("La mesa está ocupada o reservada. No se pueden agregar productos.");
        return;  // Detener el proceso si la mesa está ocupada o reservada
    }

    Producto seleccionado = null;
    int cantidad = 0;

    // HAMBURGUESAS
    if (tablaHamburguesas.getSelectionModel().getSelectedItem() != null) {
        seleccionado = tablaHamburguesas.getSelectionModel().getSelectedItem();

        if (txtCantidadH.getText().isEmpty()) {
            System.out.println("Ingresa cantidad hamburguesa");
            return;
        }

        cantidad = Integer.parseInt(txtCantidadH.getText());
    }

    // PAPAS
    else if (tablaPapas.getSelectionModel().getSelectedItem() != null) {
        seleccionado = tablaPapas.getSelectionModel().getSelectedItem();

        if (txtCantidadP.getText().isEmpty()) {
            System.out.println("Ingresa cantidad papas");
            return;
        }

        cantidad = Integer.parseInt(txtCantidadP.getText());
    }

    // BEBIDAS
    else if (tablaBebidas.getSelectionModel().getSelectedItem() != null) {
        seleccionado = tablaBebidas.getSelectionModel().getSelectedItem();

        if (txtCantidadB.getText().isEmpty()) {
            System.out.println("Ingresa cantidad bebida");
            return;
        }

        cantidad = Integer.parseInt(txtCantidadB.getText());
    }

    if (seleccionado == null) {
        System.out.println("Selecciona un producto");
        return;
    }

    if (cantidad <= 0) {
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
    
    
    
    
 @FXML
private void abrirResumenPedido() {
    // Crear una nueva ventana para mostrar el resumen
    Stage stage = new Stage();
    stage.setTitle("Resumen del Pedido");

    // Crear una tabla para mostrar los detalles del pedido
    TableView<DetallePedido> tablaResumen = new TableView<>();
    TableColumn<DetallePedido, String> colProducto = new TableColumn<>("Producto");
    TableColumn<DetallePedido, Integer> colCantidad = new TableColumn<>("Cantidad");
    TableColumn<DetallePedido, Double> colPrecio = new TableColumn<>("Precio");

    // Configurar las columnas de la tabla
    colProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

    tablaResumen.getColumns().addAll(colProducto, colCantidad, colPrecio);

    // Crear un ObservableList con los detalles de los pedidos
    ObservableList<DetallePedido> lista = FXCollections.observableArrayList();
    ProductoDAO dao = new ProductoDAO();

    // Recopilar los productos y cantidades en la lista de detalles de pedidos
    for (int i = 0; i < productos.size(); i++) {
        String nombre = productos.get(i);
        int cantidad = cantidades.get(i);
        double precio = dao.obtenerPrecio(nombre);
        lista.add(new DetallePedido(nombre, cantidad, precio));
    }

    // Establecer los elementos de la tabla con la lista de pedidos
    tablaResumen.setItems(lista);

    // Crear el botón "Confirmar Pedido"
    Button btnConfirmar = new Button("Confirmar Pedido");
    btnConfirmar.setOnAction(e -> confirmarPedido());

    // Crear el botón "Cancelar Pedido"
    Button btnCancelar = new Button("Cancelar Pedido");
    btnCancelar.setOnAction(e -> cancelarPedido());

    // Crear un layout (VBox) para la tabla y los botones
    VBox vbox = new VBox();
    vbox.getChildren().addAll(tablaResumen, btnConfirmar, btnCancelar);  // Añadir ambos botones al VBox

    // Crear una escena y mostrar la ventana
    Scene scene = new Scene(vbox, 400, 300);
    stage.setScene(scene);
    stage.show();
}










@FXML
private void cancelarPedido() {
    // Verificar si hay productos en el pedido
    if (productos.isEmpty()) {
        System.out.println("No hay productos agregados para cancelar.");
        return;  // Si no hay productos, no hace falta cancelar nada
    }

    // Obtener el ID de la mesa seleccionada
    int idMesaSeleccionada = Integer.parseInt(txtMesa.getText());
    System.out.println("ID de la mesa seleccionada para cancelar: " + idMesaSeleccionada); // Depuración

    // Crear una instancia de PedidoDAO para eliminar el pedido
    PedidoDAO pedidoDao = new PedidoDAO();

    // Eliminar los detalles del pedido (productos y cantidades)
    pedidoDao.eliminarDetallesPedido(idMesaSeleccionada);  // El método eliminarDetallesPedido debe eliminar los detalles del pedido de la base de datos

    // Eliminar el pedido de la base de datos
    pedidoDao.eliminarPedido(idMesaSeleccionada);  // El método eliminarPedido debe eliminar el pedido de la base de datos

    // Restaurar el estado de la mesa a 'libre'
    MesaDAO mesaDao = new MesaDAO();
    mesaDao.cambiarEstado(idMesaSeleccionada, "libre");

    // Limpiar la lista de productos y cantidades
    productos.clear();
    cantidades.clear();
    tablaPedido.getItems().clear();
    txtMesa.clear();

    // Confirmación de éxito
    System.out.println("Pedido cancelado correctamente.");
}


    
    
    
    
 
    
}