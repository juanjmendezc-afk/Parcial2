package co.edu.uniquindio.parcial2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;

public class InmuebleController {

    // Singleton Pattern
    private final InmobiliariaSingleton inmobiliaria = InmobiliariaSingleton.getInstancia();

    // Factory Method Pattern
    private final InmuebleFactory factory = new InmuebleFactory();

    // Componentes FXML
    @FXML private ComboBox<String> cbTipo;
    @FXML private TextField txtCiudad;
    @FXML private TextField txtHabitaciones;
    @FXML private TextField txtPisos;
    @FXML private TextField txtPrecio;
    @FXML private Button btnAgregar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnEliminar;
    @FXML private Button btnActualizar;
    @FXML private Label lblMensaje;
    @FXML private Label lblContador;

    @FXML private TableView<Inmueble> tblInmuebles;
    @FXML private TableColumn<Inmueble, String> colTipo;
    @FXML private TableColumn<Inmueble, String> colCiudad;
    @FXML private TableColumn<Inmueble, Integer> colHabitaciones;
    @FXML private TableColumn<Inmueble, Integer> colPisos;
    @FXML private TableColumn<Inmueble, Integer> colPrecio;

    private ObservableList<Inmueble> inmueblesObservable;

    @FXML
    public void initialize() {
        configurarComponentes();
        configurarEventos();
        actualizarLista();
    }

    private void configurarComponentes() {
        // Configurar tabla
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipoInmueble"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colHabitaciones.setCellValueFactory(new PropertyValueFactory<>("habitaciones"));
        colPisos.setCellValueFactory(new PropertyValueFactory<>("pisos"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        inmueblesObservable = FXCollections.observableArrayList();
        tblInmuebles.setItems(inmueblesObservable);

        // Seleccionar primer elemento del ComboBox
        cbTipo.getSelectionModel().selectFirst();
    }

    private void configurarEventos() {
        btnAgregar.setOnAction(e -> agregarInmueble());
        btnLimpiar.setOnAction(e -> limpiarFormulario());
        btnEliminar.setOnAction(e -> eliminarInmueble());
        btnActualizar.setOnAction(e -> actualizarLista());
    }

    @FXML
    private void agregarInmueble() {
        try {
            String tipo = cbTipo.getValue();
            String ciudad = txtCiudad.getText().trim();
            int habitaciones = Integer.parseInt(txtHabitaciones.getText());
            int pisos = Integer.parseInt(txtPisos.getText());
            double precio = Double.parseDouble(txtPrecio.getText());

            // Validaciones básicas
            if (ciudad.isEmpty()) {
                mostrarMensaje("La ciudad es requerida", true);
                return;
            }

            if (habitaciones < 0 || pisos < 0 || precio <= 0) {
                mostrarMensaje("Los valores numéricos deben ser positivos", true);
                return;
            }

            // Factory Method Pattern: Crear inmueble usando la fábrica
            Inmueble nuevoInmueble = factory.crearInmueble(tipo, ciudad, habitaciones, pisos, precio);

            if (nuevoInmueble != null) {
                // Singleton Pattern: Agregar a la lista global
                inmobiliaria.agregarInmueble(nuevoInmueble);
                mostrarMensaje("Inmueble agregado correctamente", false);
                limpiarFormulario();
                actualizarLista();
            } else {
                mostrarMensaje("Tipo de inmueble no válido", true);
            }

        } catch (NumberFormatException e) {
            mostrarMensaje("Por favor ingrese valores numéricos válidos", true);
        } catch (Exception e) {
            mostrarMensaje("Error al agregar inmueble: " + e.getMessage(), true);
        }
    }

    @FXML
    private void eliminarInmueble() {
        Inmueble seleccionado = tblInmuebles.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            // Singleton Pattern: Eliminar de la lista global
            inmobiliaria.getListaInmuebles().remove(seleccionado);
            mostrarMensaje("Inmueble eliminado correctamente", false);
            actualizarLista();
        } else {
            mostrarMensaje("Por favor seleccione un inmueble para eliminar", true);
        }
    }

    @FXML
    private void actualizarLista() {
        inmueblesObservable.clear();
        // Singleton Pattern: Obtener lista actualizada
        inmueblesObservable.addAll(inmobiliaria.getListaInmuebles());
        lblContador.setText("(" + inmueblesObservable.size() + " inmuebles)");
    }

    @FXML
    private void limpiarFormulario() {
        txtCiudad.clear();
        txtHabitaciones.clear();
        txtPisos.clear();
        txtPrecio.clear();
        cbTipo.getSelectionModel().selectFirst();
        lblMensaje.setVisible(false);
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        lblMensaje.setStyle(esError ? "-fx-text-fill: #e74c3c;" : "-fx-text-fill: #27ae60;");
        lblMensaje.setVisible(true);
    }
}