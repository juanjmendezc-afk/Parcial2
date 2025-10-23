package co.edu.uniquindio.parcial2.controller;

import co.edu.uniquindio.parcial2.Inmueble;
import co.edu.uniquindio.parcial2.InmuebleComponent;
import co.edu.uniquindio.parcial2.InmobiliariaSingleton;
import co.edu.uniquindio.parcial2.Decorator.DetalleDecorator;
import co.edu.uniquindio.parcial2.Decorator.ResumenDecorator;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class InmuebleController {

    // Singleton Pattern
    private final InmobiliariaSingleton inmobiliaria = InmobiliariaSingleton.getInstancia();
    @FXML public TableColumn<String, String> colExtras;
    @FXML public CheckBox chkAmoblado;
    @FXML public CheckBox chkGaraje;
    @FXML public CheckBox chkPiscina;
    @FXML public CheckBox chkJardin;

    // Componentes FXML
    @FXML private ComboBox<String> cbTipo;
    @FXML private ComboBox<String> cbVista;
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
    @FXML private TextArea txtVistaDecorada;

    @FXML private TableView<Inmueble> tblInmuebles;
    @FXML private TableColumn<Inmueble, String> colTipo;
    @FXML private TableColumn<Inmueble, String> colCiudad;
    @FXML private TableColumn<Inmueble, Integer> colHabitaciones;
    @FXML private TableColumn<Inmueble, Integer> colPisos;
    @FXML private TableColumn<Inmueble, Double> colPrecio;

    private ObservableList<Inmueble> inmueblesObservable;

    @FXML
    public void initialize() {
        configurarComponentes();
        configurarEventos();
        actualizarLista();
    }

    private void configurarComponentes() {
        // Configurar tabla
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colHabitaciones.setCellValueFactory(new PropertyValueFactory<>("habitaciones"));
        colPisos.setCellValueFactory(new PropertyValueFactory<>("pisos"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        inmueblesObservable = FXCollections.observableArrayList();
        tblInmuebles.setItems(inmueblesObservable);

        // ComboBox tipo de inmueble
        cbTipo.setItems(FXCollections.observableArrayList("Casa", "Apartamento", "Finca", "Local"));
        cbTipo.getSelectionModel().selectFirst();

        // ComboBox vista decorada
        cbVista.setItems(FXCollections.observableArrayList("Detalle", "Resumen"));
        cbVista.getSelectionModel().selectFirst();
    }

    private void configurarEventos() {
        btnAgregar.setOnAction(e -> agregarInmueble());
        btnLimpiar.setOnAction(e -> limpiarFormulario());
        btnEliminar.setOnAction(e -> eliminarInmueble());
        btnActualizar.setOnAction(e -> actualizarLista());

        // Mostrar vista decorada al seleccionar inmueble o cambiar tipo de vista
        tblInmuebles.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> mostrarVistaDecorada(newVal)
        );
        cbVista.setOnAction(e -> mostrarVistaDecorada(tblInmuebles.getSelectionModel().getSelectedItem()));
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

            // Patrón Builder: construir inmueble paso a paso
            Inmueble nuevoInmueble = new Inmueble.Builder()
                    .tipo(tipo)
                    .ciudad(ciudad)
                    .habitaciones(habitaciones)
                    .pisos(pisos)
                    .precio(precio)
                    .build();

            // Singleton Pattern: agregar inmueble a la lista global
            inmobiliaria.agregarInmueble(nuevoInmueble);
            mostrarMensaje("Inmueble agregado correctamente", false);
            limpiarFormulario();
            actualizarLista();

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
        txtVistaDecorada.clear();
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        lblMensaje.setText(mensaje);
        lblMensaje.setStyle(esError ? "-fx-text-fill: #e74c3c;" : "-fx-text-fill: #27ae60;");
        lblMensaje.setVisible(true);
    }

    /**
     * Aplica el patrón co.edu.uniquindio.parcial2.Decorator según la vista seleccionada (Detalle o Resumen)
     */
    private void mostrarVistaDecorada(Inmueble inmueble) {
        if (inmueble == null) {
            txtVistaDecorada.clear();
            return;
        }

        String vista = cbVista.getValue();
        InmuebleComponent decorado;
        if ("Detalle".equals(vista)) {
            decorado = new DetalleDecorator(inmueble);
        } else {
            decorado = new ResumenDecorator(inmueble);
        }

        txtVistaDecorada.setText(decorado.mostrarInformacion());
    }

}
