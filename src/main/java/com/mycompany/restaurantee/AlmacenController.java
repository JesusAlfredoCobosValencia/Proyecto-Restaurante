package com.mycompany.restaurantee;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class AlmacenController implements Initializable {

    @FXML private TextField txtIngrediente;
    @FXML private TextField txtUnidades;
    @FXML private TextArea txtMovimientos;
    @FXML private TableView<Inventario> tablaInventario;
    @FXML private TableColumn<Inventario, String> colIngrediente;
    @FXML private TableColumn<Inventario, Integer> colUnidades;
    @FXML private TableColumn<Inventario, Integer> colMinimo;
    @FXML private TableColumn<Inventario, String> colEstado;
    @FXML private Button btnAgregar;
    @FXML private Button btnModificar;
    @FXML private Button btnEliminar;

    private ObservableList<Inventario> lista = FXCollections.observableArrayList();
    private final InventarioDAO dao = new InventarioDAO();
    private final int MINIMO_FIJO = 10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colIngrediente.setCellValueFactory(new PropertyValueFactory<>("ingrediente"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        colMinimo.setCellValueFactory(new PropertyValueFactory<>("minimo"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        cargarTabla();
        cargarMovimientos();

        tablaInventario.setOnMouseClicked((MouseEvent event) -> {
            if(event.getClickCount() == 1){
                seleccionarItem();
            }
        });
    }

    private void cargarTabla(){
        lista.clear();
        lista.addAll(dao.verInventario());
        tablaInventario.setItems(lista);
    }
    
    private void cargarMovimientos() {
        StringBuilder sb = new StringBuilder();
        dao.verMovimientos().forEach(mov -> {
            sb.append("Ingrediente: ").append(mov.getIngrediente())
              .append(" | Acción: ").append(mov.getAccion())
              .append(" | Cantidad: ").append(mov.getCantidad())
              .append(" | Fecha: ").append(mov.getFecha())
              .append("\n");
        });
        txtMovimientos.setText(sb.toString());
    }

    private void seleccionarItem(){
        Inventario inv = tablaInventario.getSelectionModel().getSelectedItem();
        if(inv != null){
            txtIngrediente.setText(inv.getIngrediente());
            txtUnidades.setText(String.valueOf(inv.getUnidades()));
        }
    }

    @FXML
    private void agregar(ActionEvent event){
        String ingrediente = txtIngrediente.getText().trim();
        int unidades = Integer.parseInt(txtUnidades.getText().trim());

        dao.agregarIngrediente(ingrediente, unidades);
        cargarTabla();
        cargarMovimientos();
        limpiarCampos();
    }

    @FXML
    private void modificar(ActionEvent event){
        Inventario inv = tablaInventario.getSelectionModel().getSelectedItem();
        if(inv != null){
            int nuevasUnidades = Integer.parseInt(txtUnidades.getText().trim());
            dao.modificarIngrediente(inv.getIngrediente(), nuevasUnidades);
            cargarTabla();
            cargarMovimientos();
            limpiarCampos();
        }
    }

    @FXML
    private void eliminar(ActionEvent event){
        Inventario inv = tablaInventario.getSelectionModel().getSelectedItem();
        if(inv != null){
            dao.eliminarIngrediente(inv.getIngrediente(), inv.getUnidades());
            cargarTabla();
            cargarMovimientos();
            limpiarCampos();
        }
    }

    private void limpiarCampos(){
        txtIngrediente.clear();
        txtUnidades.clear();
    }

    @FXML
    private void regresarGerente(ActionEvent event){
        MetodosGen.cambiarVista(event, "Administrador.fxml");
    }  
}