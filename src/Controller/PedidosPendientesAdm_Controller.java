package Controller;

import Model.ConnectionDB;
import Model.Pedido;
import Model.ShoppingList_Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class PedidosPendientesAdm_Controller {

    @FXML
    private TableView<Pedido> tbl_pedidos;

    @FXML
    private Button btn_marcarEntregado;

    @FXML
    private Button btn_marcarEnviado;

    @FXML
    private TableColumn<Pedido,String> clm_pedidos;

    @FXML
    private TableView<ShoppingList_Product> tbl_productos;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_producto;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_cantidad;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_precioUnit;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_precioTotal;

    @FXML
    private Label lbl_fechaHora;

    @FXML
    private Label lbl_montoTotal;

    @FXML
    protected void initialize(){
        fillTablePedidos();
        tbl_pedidos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> mostrarInfo());
    }

    private void fillTablePedidos(){
        clm_pedidos.setCellValueFactory(cellData -> cellData.getValue().fechaPedidoProperty());
        ObservableList<Pedido> listPedidos = FXCollections.observableArrayList();
        listPedidos.addAll(ConnectionDB.getInstance().selectPedidos_pendientes());
        tbl_pedidos.setItems(listPedidos);
    }

    @FXML
    private void mostrarInfo(){
        if(tbl_pedidos.getSelectionModel().getSelectedItem().getEstadoPedido().equals("Pendiente")){
            btn_marcarEnviado.setVisible(true);
            btn_marcarEntregado.setVisible(false);
        }
        if(tbl_pedidos.getSelectionModel().getSelectedItem().getEstadoPedido().equals("Enviado")){
            btn_marcarEntregado.setVisible(true);
            btn_marcarEnviado.setVisible(false);
        }
        lbl_fechaHora.setText(tbl_pedidos.getSelectionModel().getSelectedItem().getFechaPedido());
        lbl_montoTotal.setText(tbl_pedidos.getSelectionModel().getSelectedItem().getTotalAPagar());
        fillTableProductos();
    }

    private void fillTableProductos(){
        clm_producto.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        clm_cantidad.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductQuantity())));
        clm_precioUnit.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductPrize())));
        clm_precioTotal.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductQuantity()*cellData.getValue().getProductPrize())));
        ObservableList<ShoppingList_Product> listProductos = FXCollections.observableArrayList();
        listProductos.addAll(tbl_pedidos.getSelectionModel().getSelectedItem().getListaProductos());
        tbl_productos.setItems(listProductos);
    }

    @FXML
    private void marcarEntregado(){
        if(tbl_pedidos.getSelectionModel().getSelectedItem()!=null) {
            boolean response = ConnectionDB.getInstance().SetDelivered(Integer.toString(tbl_pedidos.getSelectionModel().getSelectedItem().getIdPedido()));
            if (response) {
                Main.MessageBox("Éxito", "Se ha marcado el pedido como entregado exitosamente.");
                tbl_pedidos.setItems(null);
                fillTablePedidos();
                tbl_productos.setItems(null);
                lbl_fechaHora.setText("");
                lbl_montoTotal.setText("");

            } else {
                Main.MessageBox("Error", "No se ha podido marcar el pedido como entregado.");
            }
        }
        else{
            Main.MessageBox("Error", "Seleccione un pedido para poder marcarlo como entregado.");
        }
    }

    @FXML
    private void marcarEnviado(){
        if(tbl_pedidos.getSelectionModel().getSelectedItem()!=null) {
            boolean response = ConnectionDB.getInstance().SetSend(Integer.toString(tbl_pedidos.getSelectionModel().getSelectedItem().getIdPedido()));
            if (response){
                Main.MessageBox("Éxito", "Se ha marcado el pedido como entregado exitosamente.");
                tbl_pedidos.setItems(null);
                fillTablePedidos();
                tbl_productos.setItems(null);
                lbl_fechaHora.setText("");
                lbl_montoTotal.setText("");
            } else {
                Main.MessageBox("Error", "No se ha podido marcar el pedido como entregado.");
            }
        }
        else{
            Main.MessageBox("Error", "Seleccione un pedido para poder marcarlo como entregado.");
        }
    }

    public void GoBack(ActionEvent event) {
        try {
            FXRouter.goTo("MenuAdm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
