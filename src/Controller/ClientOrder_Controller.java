package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;
import java.io.IOException;

public class ClientOrder_Controller {

    @FXML
    private Button btn_cancelarPedido;

    @FXML
    private TextField TextBox_ProductQuantity;

    @FXML
    private MenuBar id_menuBar;

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
    @FXML private TableView<ShoppingList_Product> tablaView_Inventario;
    @FXML private TableColumn<ShoppingList_Product, String> tableColumn_Producto_ListaCompra;
    @FXML private TableColumn<ShoppingList_Product, String> tableColumn_Precio_ListaCompra;
    @FXML private TableColumn<ShoppingList_Product, String> tableColumn_Cantidad_ListaCompra;

    @FXML
    private ChoiceBox<String> choiceBox_Sucursal;

    @FXML private Label Label_TotalCost;
    private int totalCost;

    @FXML
    protected void initialize(){
        if(User.getCurrentUser().getUserType().equals("Empleado")){
            id_menuBar.setDisable(true);
            id_menuBar.setVisible(false);
        }
        else{
            btn_cancelarPedido.setDisable(true);
            btn_cancelarPedido.setVisible(false);
        }
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
        dulces_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Postre"));
        tablaView_Dulce.setItems(dulces_list);
    }

    @FXML
    void MakeOrder(ActionEvent event) {
        if(!tablaView_Inventario.getItems().isEmpty()){
            Pair<ObservableList,String> tupla = new Pair(tablaView_Inventario.getItems(), Label_TotalCost.getText());
            if (User.getCurrentUser().getUserType().equals("Cliente")){
                try {FXRouter.goTo("OnlinePayment", tupla);}
                catch (IOException e) {e.printStackTrace();}
            }else{
                try {FXRouter.goTo("LocalPayment", tupla);}
                catch (IOException e) {e.printStackTrace();}
            }

        }else{
            Main.MessageBox("Tabla de productos vacía", "Seleccione los productos que desea comprar.");
        }
    }

    @FXML
    void RemoveProduct(ActionEvent event) {
        if(!tablaView_Inventario.getItems().isEmpty()){
            totalCost = totalCost - tablaView_Inventario.getSelectionModel().getSelectedItem().getProductPrize();
            int cantidadProducto = tablaView_Inventario.getSelectionModel().getSelectedItem().getProductQuantity();
            Label_TotalCost.setText(String.valueOf(totalCost));
            if( cantidadProducto > 1){
                tablaView_Inventario.getSelectionModel().getSelectedItem().setProductQuantity(cantidadProducto - 1);
            }else{
                tablaView_Inventario.getItems().remove(tablaView_Inventario.getSelectionModel().getSelectedItem());
            }
        }
    }

    @FXML
    void SelectProduct(ActionEvent event) {
        if (TextBox_ProductQuantity.getText().matches("-?([1-9][0-9]*)?") && !TextBox_ProductQuantity.getText().equals("")) {
            if (!tablaView_Bebida.getSelectionModel().isEmpty()) {
                checkProduct(tablaView_Bebida.getSelectionModel().getSelectedItem());
            } else if (!tablaView_Comida.getSelectionModel().isEmpty()) {
                checkProduct(tablaView_Comida.getSelectionModel().getSelectedItem());
            } else if (!tablaView_Dulce.getSelectionModel().isEmpty()) {
                checkProduct(tablaView_Dulce.getSelectionModel().getSelectedItem());
            } else {
                Main.MessageBox("No se ha seleccionado el producto", "Seleccione el producto que desea agregar a su pedido");
            }
            TextBox_ProductQuantity.clear();
        }else{
            Main.MessageBox("No se ha indicado la cantidad de producto deseada", "Indique la cantidad de producto que desea agregar a su pedido");
        }
    }

    @FXML
    void mostrarHistorialPedidos(ActionEvent event) {
        try {
            FXRouter.goTo("ClientOrderHistory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut(ActionEvent event) {
        try {
            FXRouter.goTo("Login");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkProduct(Product selection) {
        int indice = -1;
        for (int i = 0; i < tablaView_Inventario.getItems().size(); i++) {
            if (tablaView_Inventario.getItems().get(i).getProductID().equals(selection.getProductID())) {
                indice = i;
                break;
            }
        }
        if (indice == -1) { // Si no existe en la tabla inventario
            tablaView_Inventario.getItems().add(new ShoppingList_Product(selection.getProductName(), selection.getProductType(), selection.getProductID(), selection.getProductPrize(), selection.getProductDetail(), selection.getProductState(), Integer.parseInt(TextBox_ProductQuantity.getText())));
        } else { // Si ya existe
            tablaView_Inventario.getItems().get(indice).setProductQuantity(tablaView_Inventario.getItems().get(indice).getProductQuantity() + Integer.parseInt(TextBox_ProductQuantity.getText()));
        }
        totalCost = totalCost + (selection.getProductPrize() * Integer.parseInt(TextBox_ProductQuantity.getText()));
        Label_TotalCost.setText(String.valueOf(totalCost) + " colones");
        tablaView_Bebida.getSelectionModel().clearSelection();
        tablaView_Comida.getSelectionModel().clearSelection();
        tablaView_Dulce.getSelectionModel().clearSelection();
        // clearSelection(null);
    }

    @FXML
    private void ContactoMessage(){
        Main.MessageBox("Contacto","Para contactar con el restaurante comuniquese al: \r\n +506 8618 4965");
    }

    @FXML
    private void cancelarPedido(){
        try {
            FXRouter.goTo("MenuAdm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
