package Controller;

import Model.ConnectionDB;
import Model.ShoppingList_Product;
import Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Model.Pedido;

import java.io.IOException;

public class ClientOrderHistory_Controller {
    @FXML
    private TableView<Pedido> tbl_pedidos;

    @FXML
    private TableColumn<Pedido, String> clm_pedidos;

    @FXML
    private TableView<ShoppingList_Product> tbl_ProductosPedido;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_producto;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_cantidad;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_precio;

    @FXML
    private Label lbl_fechaHora;

    @FXML
    private Label lbl_estado;

    @FXML
    private Label lbl_productos;

    @FXML
    private Label lbl_montoTotal;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_precioTotalProducto;

    @FXML
    protected void initialize(){
        fillTablePedidos();
        tbl_pedidos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarInfo());
    }

    private void fillTablePedidos(){
        clm_pedidos.setCellValueFactory(cellData -> cellData.getValue().fechaPedidoProperty());
        ObservableList<Pedido> listPedidos = FXCollections.observableArrayList();
        listPedidos.addAll(ConnectionDB.getInstance().selectPedidos_porCliente());
        tbl_pedidos.setItems(listPedidos);
    }
    @FXML
    private void mostrarInfo(){
        lbl_fechaHora.setText(tbl_pedidos.getSelectionModel().getSelectedItem().getFechaPedido());
        lbl_estado.setText(tbl_pedidos.getSelectionModel().getSelectedItem().getEstadoPedido());
        lbl_montoTotal.setText(tbl_pedidos.getSelectionModel().getSelectedItem().getTotalAPagar());
        fillTableProductos();
    }

    private void fillTableProductos(){
        clm_producto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        clm_cantidad.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductQuantity())));
        clm_precio.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductPrize())));
        clm_precioTotalProducto.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductQuantity()*cellData.getValue().getProductPrize())));
        ObservableList<ShoppingList_Product> listProductos = FXCollections.observableArrayList();
        listProductos.addAll(tbl_pedidos.getSelectionModel().getSelectedItem().getListaProductos());
        tbl_ProductosPedido.setItems(listProductos);
    }

    @FXML
    public void RealizarOrden(ActionEvent event) {
        try {
            FXRouter.goTo("Client");
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    @FXML

    public void LogOut(ActionEvent event) {
        try {
            FXRouter.goTo("Login");
        } catch (IOException e) {
            System.out.print(e);
        }
    }
}

