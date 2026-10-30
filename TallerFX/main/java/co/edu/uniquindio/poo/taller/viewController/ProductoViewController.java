package co.edu.uniquindio.poo.taller.viewController;

import co.edu.uniquindio.poo.taller.App;
import co.edu.uniquindio.poo.taller.controller.ProductoController;
import co.edu.uniquindio.poo.taller.model.Producto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import static co.edu.uniquindio.poo.taller.App.empresa;

public class ProductoViewController {

    ProductoController productoController;
    ObservableList<Producto> listaProductos = FXCollections.observableArrayList();
    Producto selectedProducto;

    @FXML
    private TableColumn<Producto, String> tbcId;

    @FXML
    private TextField txtId;

    @FXML
    private TableColumn<Producto, Integer> tbcStock;

    @FXML
    private TextField txtName;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TableColumn<Producto, String> tbcName;

    @FXML
    private Button btnEliminar;

    @FXML
    private TableView<Producto> tblListProducto;

    @FXML
    private TextField txtStock;

    @FXML
    private Button btnActualizarProducto;

    private App app;

    @FXML
    void onAgregarProducto() {
        agregarProducto();
    }

    @FXML
    void onActualizarProducto() {
        actualizarProducto();
    }

    @FXML
    void onEliminar() {
        eliminarProducto();
    }

    @FXML
    void onLimpiar() {
        limpiarSeleccion();
    }

    @FXML
    void initialize() {
        productoController = new ProductoController(empresa);
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerProductos();
        tblListProducto.getItems().clear();
        tblListProducto.setItems(listaProductos);
        listenerSelection();
    }

    private void initDataBinding() {
        tbcId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getiD()));
        tbcName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        tbcStock.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject());
    }

    private void obtenerProductos() {
        listaProductos.addAll(productoController.obtenerListaProductos());
    }

    private void listenerSelection() {
        tblListProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProducto = newSelection;
            mostrarInformacionProducto(selectedProducto);
        });
    }

    private void mostrarInformacionProducto(Producto producto) {
        if (producto != null) {
            txtId.setText(producto.getiD());
            txtName.setText(producto.getName());
            txtStock.setText(String.valueOf(producto.getStock()));
        }
    }

    private void agregarProducto() {
        Producto producto = buildProducto(txtId.getText());
        if (producto != null && productoController.crearProducto(producto)) {
            listaProductos.add(producto);
            limpiarCamposProducto();
        }
    }

    private Producto buildProducto(String iD) {
        try {
            return new Producto(iD, txtName.getText(), Integer.parseInt(txtStock.getText().trim()));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private void eliminarProducto() {
        if (selectedProducto != null && productoController.eliminarProducto(selectedProducto.getiD())) {
            listaProductos.remove(selectedProducto);
            limpiarCamposProducto();
            limpiarSeleccion();
        }
    }

    private void actualizarProducto() {
        if (selectedProducto != null) {
            Producto producto = buildProducto(txtId.getText().trim());
            if (producto != null && !producto.getiD().isEmpty()
                    && productoController.actualizarProducto(selectedProducto.getiD(), producto)) {
                tblListProducto.refresh();
                limpiarSeleccion();
                limpiarCamposProducto();
            }
        }
    }

    private void limpiarSeleccion() {
        tblListProducto.getSelectionModel().clearSelection();
        limpiarCamposProducto();
    }

    private void limpiarCamposProducto() {
        txtId.clear();
        txtName.clear();
        txtStock.clear();
    }

    public void setApp(App app) {
        this.app = app;
    }
}