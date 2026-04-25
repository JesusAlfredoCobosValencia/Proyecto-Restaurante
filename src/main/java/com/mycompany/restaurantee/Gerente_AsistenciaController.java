/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.restaurantee;

import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author elcre
 */
public class Gerente_AsistenciaController implements Initializable{

@FXML
    private ComboBox<Integer> cbAnio;

    @FXML
    private ComboBox<String> cbMes;

    @FXML
    private GridPane gridCalendario;

    @FXML
    private Label lblFechaSeleccionada;

    @FXML
    private TableView<Asistencia> tablaAsistencia;

    @FXML
    private TableColumn<Asistencia, Integer> colId;

    @FXML
    private TableColumn<Asistencia, String> colNombre;

    @FXML
    private TableColumn<Asistencia, String> colEstado;

    @FXML
    private Button btnPresente;

    @FXML
    private Button btnFalta;

    private AsistenciaDAO asistenciaDAO = new AsistenciaDAO();
    private String fechaSeleccionada = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        inicializarCombos();
        inicializarTabla();

        LocalDate hoy = LocalDate.now();
        cbAnio.setValue(hoy.getYear());
        cbMes.getSelectionModel().select(hoy.getMonthValue() - 1);

        dibujarCalendario();

        fechaSeleccionada = hoy.toString();
        lblFechaSeleccionada.setText("             " + fechaSeleccionada);
        cargarAsistenciaPorFecha();
    }

    private void inicializarCombos(){
        cbMes.getItems().addAll(
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        );

        for(int i = 2024; i <= 2035; i++){
            cbAnio.getItems().add(i);
        }
    }

    private void inicializarTabla(){
        colId.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    @FXML
    private void cambiarMesOAnio(ActionEvent event){
        dibujarCalendario();
    }

    private void dibujarCalendario(){
        gridCalendario.getChildren().clear();

        if(cbAnio.getValue() == null || cbMes.getValue() == null){
            return;
        }

        int anio = cbAnio.getValue();
        int mes = cbMes.getSelectionModel().getSelectedIndex() + 1;

        YearMonth yearMonth = YearMonth.of(anio, mes);
        LocalDate primerDia = LocalDate.of(anio, mes, 1);

        int diasDelMes = yearMonth.lengthOfMonth();
        int diaSemanaInicio = primerDia.getDayOfWeek().getValue();

        String[] encabezados = {"   L ", "  M", "  M", "   J", "   V", "   S", "   D"};

        for(int col = 0; col < 7; col++){
            Label lblDia = new Label(encabezados[col]);
            lblDia.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
            lblDia.setPrefWidth(28);
            lblDia.setPrefHeight(20);
            gridCalendario.add(lblDia, col, 0);
        }

        int fila = 1;
        int columna = diaSemanaInicio - 1;

        for(int dia = 1; dia <= diasDelMes; dia++){
            Button btnDia = new Button(String.valueOf(dia));
            btnDia.setPrefWidth(30);
            btnDia.setPrefHeight(30);

            int diaFinal = dia;
            btnDia.setOnAction(e -> seleccionarDia(diaFinal));

            gridCalendario.add(btnDia, columna, fila);

            columna++;
            if(columna > 6){
                columna = 0;
                fila++;
            }
        }
    }

    private void seleccionarDia(int dia){
        int anio = cbAnio.getValue();
        int mes = cbMes.getSelectionModel().getSelectedIndex() + 1;

        String mesTexto = (mes < 10) ? "0" + mes : String.valueOf(mes);
        String diaTexto = (dia < 10) ? "0" + dia : String.valueOf(dia);

        fechaSeleccionada = anio + "-" + mesTexto + "-" + diaTexto;
        lblFechaSeleccionada.setText("             " + fechaSeleccionada);

        cargarAsistenciaPorFecha();
    }

    private void cargarAsistenciaPorFecha(){
        ObservableList<Asistencia> datos = FXCollections.observableArrayList(
                asistenciaDAO.verAsistenciaPorFecha(fechaSeleccionada)
        );
        tablaAsistencia.setItems(datos);
    }

    @FXML
    private void marcarPresente(ActionEvent event){
        Asistencia asistenciaSeleccionada = tablaAsistencia.getSelectionModel().getSelectedItem();

        if(fechaSeleccionada.isEmpty()){
            System.out.println("Selecciona un día en el calendario");
            return;
        }

        if(asistenciaSeleccionada == null){
            System.out.println("Selecciona un empleado");
            return;
        }

        asistenciaDAO.guardarAsistencia(asistenciaSeleccionada.getIdUsuario(), fechaSeleccionada, "✓");
        cargarAsistenciaPorFecha();
        System.out.println("Asistencia marcada como presente");
    }

    @FXML
    private void marcarFalta(ActionEvent event){
        Asistencia asistenciaSeleccionada = tablaAsistencia.getSelectionModel().getSelectedItem();

        if(fechaSeleccionada.isEmpty()){
            System.out.println("Selecciona un día en el calendario");
            return;
        }

        if(asistenciaSeleccionada == null){
            System.out.println("Selecciona un empleado");
            return;
        }

        asistenciaDAO.guardarAsistencia(asistenciaSeleccionada.getIdUsuario(), fechaSeleccionada, "✗");
        cargarAsistenciaPorFecha();
        System.out.println("Asistencia marcada como falta");
    }

    @FXML
    private void regresarGerente(ActionEvent event){
        MetodosGen.cambiarVista(event, "Administrador.fxml");
    }
}