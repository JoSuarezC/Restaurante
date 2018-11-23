package Controller;

import Model.ConnectionDB;
import Model.ShoppingList_Product;
import Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Pair;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientLocalPayment_Controller {

    @FXML
    private CheckBox creditCardCheck;

    @FXML
    private TableView<ShoppingList_Product> inventoryTable;

    @FXML
    private TableColumn<ShoppingList_Product, String> quantityColumn;

    @FXML
    private CheckBox chequeCheck;

    @FXML
    private CheckBox bankTransferCheck;

    @FXML
    private CheckBox cashCheck;

    @FXML
    private TableColumn<ShoppingList_Product, String> productColumn;

    @FXML
    private Label Label_TotalCost;

    @FXML
    private Label Label_Sucursal;

    @FXML
    protected void initialize(){ // El boton de atras de la siguiente pantalla me va a joder esto pero bueno, luego veo que hacer xd
        Pair<ObservableList,String> tupla = ((Pair<ObservableList, String>) FXRouter.getData());
        // Table
        ObservableList<ShoppingList_Product> inventoryList = tupla.getKey();
        productColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        inventoryTable.setItems(inventoryList);
        // Label
        Label_TotalCost.setText(tupla.getValue());
        Label_Sucursal.setText(User.getCurrentUser().getSucursalName());
    }

    @FXML
    void handleCheckBoxCard(ActionEvent event){
        if(creditCardCheck.isSelected()){
            chequeCheck.setSelected(false);
            cashCheck.setSelected(false);
            bankTransferCheck.setSelected(false);
        }
    }

    @FXML
    void handleCheckBoxCheque(ActionEvent event){
        if(chequeCheck.isSelected()){
            creditCardCheck.setSelected(false);
            cashCheck.setSelected(false);
            bankTransferCheck.setSelected(false);
        }
    }

    @FXML
    void handleCheckBoxCash(ActionEvent event){
        if(cashCheck.isSelected()){
            creditCardCheck.setSelected(false);
            chequeCheck.setSelected(false);
            bankTransferCheck.setSelected(false);
        }
    }

    @FXML
    void handleCheckBoxTransfer(ActionEvent event){
        if(bankTransferCheck.isSelected()){
            creditCardCheck.setSelected(false);
            chequeCheck.setSelected(false);
            cashCheck.setSelected(false);
        }
    }

    @FXML
    void back(ActionEvent event) {
        try {
            FXRouter.goTo("Client",1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void payOrder(ActionEvent event) {
        if (!getCheckBox().equals("Sin selección")) {
            Date fecha = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m");
            String orderID;
            String totalAPagar = Label_TotalCost.getText();
            orderID = ConnectionDB.getInstance().makeOrder(User.getCurrentUser().getUserID(), dateFormat.format(fecha), "Orden Local", totalAPagar, Label_Sucursal.getText(), "Orden Local", "Entregado");
            System.out.print(orderID);
            if(orderID == null){
               Main.MessageBox("Error","No se ha completado la transacción");
            }else{
                ObservableList<ShoppingList_Product> list;
                list = inventoryTable.getItems();
                int prize;
                for (ShoppingList_Product i : list) {
                    prize = i.getProductPrize() * i.getProductQuantity();
                    ConnectionDB.getInstance().buyProduct(i.getProductID(), String.valueOf(i.getProductQuantity()), orderID, String.valueOf(prize));
                }
                generateBill(orderID, totalAPagar, getCheckBox());
                Main.MessageBox("Éxito", "Su orden ha sido creada exitosamente");
                try {
                    FXRouter.goTo("Client");
                } catch (IOException e) {
                    System.out.print(e);
                }
            }

        } else {
            Main.MessageBox("Espacios vacíos", "Indique el tipo de pago del pedido");
        }
    }

    private void generateBill(String orderID, String total,String formaPago){
        ConnectionDB.getInstance().generateBill(formaPago, "", orderID,total);
    }

    private String getCheckBox() {
        if(bankTransferCheck.isSelected()){
           return "Transferencia bancaria";
        }else if (creditCardCheck.isSelected()){
            return "Tarjeta bancaria";
        }else if (chequeCheck.isSelected()){
            return "Cheque";
        }else if (cashCheck.isSelected()) {
            return "Efectivo";
        }else{
            return "Sin selección";
        }
    }

}
