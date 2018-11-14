package Controller;

import Model.ConnectionDB;
import Model.Product;
import Model.ShoppingList_Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientOrder_Controller {

    @FXML
    private TextField TextBox_ProductQuantity;

    // Combos //
    @FXML private TableView<Product> tablaView_Combos;
    @FXML private TableColumn<Product, String> tableColumn_Producto_Combos;
    @FXML private TableColumn<Product, String> tableColumn_Precio_Combos;

    // Postre //
    @FXML private TableView<Product> tablaView_Dulce;
    @FXML private TableColumn<Product, String> tableColumn_Producto_Dulce;
    @FXML private TableColumn<Product, String> tableColumn_Precio_Dulce;

    // Bebida //
    @FXML private TableView<Product> tablaView_Bebida;
    @FXML private TableColumn<Product, String> tableColumn_Producto_Bebida;
    @FXML private TableColumn<Product, String> tableColumn_Precio_Bebida;

    // Comida //
    @FXML private TableView<Product> tablaView_Comida;
    @FXML private TableColumn<Product, String> tableColumn_Producto_Comida;
    @FXML private TableColumn<Product, String> tableColumn_Precio_Comida;


    // ListaCompra //
    @FXML private TableView<Product> tablaView_Inventario;
    @FXML private TableColumn<ShoppingList_Product, String> tableColumn_Producto_ListaCompra;
    @FXML private TableColumn<ShoppingList_Product, String> tableColumn_Precio_ListaCompra;
    @FXML private TableColumn<ShoppingList_Product, String> tableColumn_Cantidad_ListaCompra;

    private int totalCost;

    @FXML
    protected void initialize(){

        fillTables();

    }

    private void fillTables(){
        totalCost = 0;
        // Lista compra
        tableColumn_Cantidad_ListaCompra.setCellValueFactory(cellData -> cellData.getValue().productQuantityProperty().asString());
        tableColumn_Producto_ListaCompra.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tableColumn_Precio_ListaCompra.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());

        // Comida
        tableColumn_Producto_Comida.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tableColumn_Precio_Comida.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ObservableList<Product> comida_list = FXCollections.observableArrayList();
        comida_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Comida"));
        tablaView_Comida.setItems(comida_list);

        // Bebida
        tableColumn_Producto_Bebida.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tableColumn_Precio_Bebida.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ObservableList<Product> bebidas_list = FXCollections.observableArrayList();
        bebidas_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Bebida"));
        tablaView_Bebida.setItems(bebidas_list);

        // Dulces
        tableColumn_Producto_Dulce.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        tableColumn_Precio_Dulce.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ObservableList<Product> dulces_list = FXCollections.observableArrayList();
        dulces_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Dulce"));
        tablaView_Dulce.setItems(dulces_list);
    }

    @FXML
    void MakeOrder(ActionEvent event) {

    }

    @FXML
    void RemoveProduct(ActionEvent event) {

    }

    @FXML
    void SelectProduct(ActionEvent event) {

    }

    @FXML
    void mostrarHistorialPedidos(ActionEvent event) {

    }

    @FXML
    void CerrarSesion(ActionEvent event) {

    }

}
