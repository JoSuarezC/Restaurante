package Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        ObservableList<String> brachOffice_list = FXCollections.observableArrayList();
        brachOffice_list.addAll(ConnectionDB.getInstance().selectSucursales());
        choiceBox_Sucursal.setItems(brachOffice_list);
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
            String OrderType = MessageOrderType();
            if (!OrderType.equals("Error")){
                String formaPago = MessagePayment();
                String creditCard;
                if(formaPago.equals("Tarjeta de crédito")){creditCard = MessageCreditCard(); }
                if(!formaPago.equals("Error") ){
                    Date fecha = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m");
                    String orderID;
                    String totalAPagar = Label_TotalCost.getText();
                    if (User.getCurrentUser().getUserType().equals("Empleado")){
                        orderID = ConnectionDB.getInstance().makeOrder(null, dateFormat.format(fecha).toString(), "Local", totalAPagar);
                    }else{
                        orderID = ConnectionDB.getInstance().makeOrder(User.getCurrentUser().getUserID(), dateFormat.format(fecha).toString(), OrderType, totalAPagar);
                    }
                    System.out.print(orderID);
                    ObservableList<ShoppingList_Product> list;
                    list = tablaView_Inventario.getItems();
                    int prize = 0;
                    for (ShoppingList_Product i : list) {
                        prize = i.getProductPrize() * i.getProductQuantity();
                        ConnectionDB.getInstance().buyProduct(i.getProductID(),String.valueOf(i.getProductQuantity()),orderID, String.valueOf(prize));
                    }
                    tablaView_Inventario.getItems().clear();
              //      generateBill(orderID);
                }
            }
        }else{
            Main.MessageBox("Tabla de productos vacía", "Seleccione los productos que desea comprar.");
        }
    }

    @FXML
    void RemoveProduct(ActionEvent event) {
        if(!tablaView_Inventario.getItems().isEmpty()){
            int i = tablaView_Inventario.getSelectionModel().getSelectedIndex();
            tablaView_Inventario.getItems().remove(i);
        }
    }

    @FXML
    void SelectProduct(ActionEvent event) {
        if (TextBox_ProductQuantity.getText().matches("-?([1-9][0-9]*)?") && !TextBox_ProductQuantity.getText().equals("")) {
            if (tablaView_Bebida.getSelectionModel().getSelectedIndex() != -1) {
                checkProduct(tablaView_Bebida.getSelectionModel().getSelectedItem());
                totalCost = totalCost + (tablaView_Bebida.getSelectionModel().getSelectedItem().getProductPrize() * Integer.parseInt(TextBox_ProductQuantity.getText()));
                Label_TotalCost.setText(String.valueOf(totalCost) + " colones");
                tablaView_Bebida.getSelectionModel().clearSelection();
            } else if (tablaView_Comida.getSelectionModel().getSelectedIndex() != -1) {
                checkProduct(tablaView_Comida.getSelectionModel().getSelectedItem());
                totalCost = totalCost + (tablaView_Comida.getSelectionModel().getSelectedItem().getProductPrize() * Integer.parseInt(TextBox_ProductQuantity.getText()));
                Label_TotalCost.setText(String.valueOf(totalCost) + " colones");
                tablaView_Comida.getSelectionModel().clearSelection();
            } else if (tablaView_Dulce.getSelectionModel().getSelectedIndex() != -1) {
                checkProduct(tablaView_Dulce.getSelectionModel().getSelectedItem());
                totalCost = totalCost + (tablaView_Dulce.getSelectionModel().getSelectedItem().getProductPrize() * Integer.parseInt(TextBox_ProductQuantity.getText()));
                Label_TotalCost.setText(String.valueOf(totalCost) + " colones");
                tablaView_Dulce.getSelectionModel().clearSelection();
            } else {
                Main.MessageBox("No se ha seleccionado el producto", "Seleccione el producto que desea agregar a su pedido");
            }
        }else{
            Main.MessageBox("No se ha indicado la cantidad de producto deseada", "Indique la cantidad de producto que desea agregar a su pedido");
        }
    }

    @FXML
    void mostrarHistorialPedidos(ActionEvent event) {
        try {
            FXRouter.goTo("ClientOrderHistory");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    void CerrarSesion(ActionEvent event) {

    }

    private void checkProduct(Product selection){
        int indice = -1;
        for (int i = 0; i < tablaView_Inventario.getItems().size(); i ++) {
            if (tablaView_Inventario.getItems().size() == 0) {
                break;
            } else if (tablaView_Inventario.getItems().get(i).getProductID().equals(selection.getProductID())) {
                indice = i;
                break;
            }
        }
        if(indice != -1){
            tablaView_Inventario.getItems().get(indice).setProductQuantity(tablaView_Inventario.getItems().get(indice).getProductQuantity() + Integer.parseInt(TextBox_ProductQuantity.getText()));
        }else{
            tablaView_Inventario.getItems().add(new ShoppingList_Product(selection.getProductName(), selection.getProductType(), selection.getProductID(), selection.getProductPrize(), selection.getProductDetail(), Integer.parseInt(TextBox_ProductQuantity.getText())));
        }
    }

    private String MessageOrderType(){
        try{
            List<String> choices = new ArrayList<>();
            choices.add("Express");
            choices.add("Pasar a retirar");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Express", choices);
            dialog.setTitle("Indique la forma de recibir su pedido");
            dialog.setHeaderText("Seleccione la forma de recibir su pedido");
            dialog.setContentText("Pedido: ");
          //  dialog.setGraphic(new ImageView(this.getClass().getResource("View/img/store.png").toString()));
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(letter -> System.out.println("Your choice: " + letter));
            return result.get();
        }catch (Exception e){return "Error";}
    }

    private String MessagePayment(){
        try{
            List<String> choices = new ArrayList<>();
            choices.add("Tarjeta de crédito");
            if(User.getCurrentUser().getUserType().equals("Empleado")){
                choices.add("Efectivo");
                choices.add("Cheque");
                choices.add("Transferencia");
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Tarjeta de crédito", choices);
            dialog.setTitle("Indique la forma de pago del pedido");
            dialog.setHeaderText("Seleccione la forma de pago de su pedido");
            dialog.setContentText("Forma de pago: ");
            //  dialog.setGraphic(new ImageView(this.getClass().getResource("View/img/store.png").toString()));
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(letter -> System.out.println("Your choice: " + letter));
            return result.get();
        }catch (Exception e){return "Error";}
    }


    private String MessageCreditCard(){
        try{
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Indique el número de tarjeta de crédito");
            dialog.setHeaderText("Indique el número de tarjeta de crédito");
            dialog.setContentText("Tarjeta de crédito: ");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(name -> System.out.println("Your name: " + name));
            return result.get();
        }catch (Exception e){return "Error";}
    }

}
