package Controller;

import Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientOnlinePayment_Controller {


    @FXML
    private CheckBox bankTransferCheck;

    @FXML
    private TextArea directionTF;

    @FXML
    private ChoiceBox<Sucursal> choiceBox_Sucursal;

    @FXML
    private ChoiceBox<String> choiceBox_Entrega;

    @FXML
    private CheckBox creditCardCheck;

    @FXML
    private TableView<ShoppingList_Product> inventoryTable;

    @FXML
    private TableColumn<ShoppingList_Product, String> quantityColumn;

    @FXML
    private Label Label_TotalCost;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField codeTF;

    @FXML
    private TextField creditCardTF;

    @FXML
    private DatePicker datePicker;


    @FXML
    private TableColumn<ShoppingList_Product, String> productColumn;

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
        // Sucursal Choicebox
        ObservableList<Sucursal> sucursales_list = FXCollections.observableArrayList();
        sucursales_list.addAll(ConnectionDB.getInstance().selectSucursales());
        choiceBox_Sucursal.setItems(sucursales_list);
        // Entrega Choicebox
        choiceBox_Entrega.getItems().setAll("Express","Retirar en el restaurante");
        choiceBox_Entrega.getSelectionModel().selectFirst();
        ChangeListener<String> changeListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, //
                                String oldValue, String newValue) {
                if(newValue.equals("Retirar en el restaurante")){
                    directionTF.setText("En la sucursal indicada");
                    directionTF.setDisable(true);
                }else{
                    directionTF.clear();
                    directionTF.setDisable(false);
                }
            }
        };
        choiceBox_Entrega.getSelectionModel().selectedItemProperty().addListener(changeListener);
    }

    @FXML
    void payOrder(ActionEvent event) {
        if(!nameTF.getText().equals("") && !codeTF.getText().equals("") && !creditCardTF.getText().equals("") && !datePicker.equals("") && !choiceBox_Sucursal.getSelectionModel().getSelectedItem().equals(null)) {
            if (validaciones()) {
                Date fecha = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m");
                String orderID;
                String totalAPagar = Label_TotalCost.getText();
                orderID = ConnectionDB.getInstance().makeOrder(User.getCurrentUser().getUserID(), dateFormat.format(fecha), choiceBox_Entrega.getSelectionModel().getSelectedItem(), totalAPagar, choiceBox_Sucursal.getValue().toString(), directionTF.getText());
                System.out.print(orderID);
                ObservableList<ShoppingList_Product> list;
                list = inventoryTable.getItems();
                int prize;
                for (ShoppingList_Product i : list) {
                    prize = i.getProductPrize() * i.getProductQuantity();
                    ConnectionDB.getInstance().buyProduct(i.getProductID(), String.valueOf(i.getProductQuantity()), orderID, String.valueOf(prize));
                }
                generateBill(orderID, totalAPagar);
                Main.MessageBox("Éxito", "Su orden ha sido creada exitosamente");
                try {
                    FXRouter.goTo("Client");
                } catch (IOException e) {
                    System.out.print(e);
                }
            }
        }
        else{
            Main.MessageBox("Error", "Rellene todas las casilla necesarias para realizar el pedido.");
        }
    }

    private void generateBill(String orderID, String total){
        ConnectionDB.getInstance().generateBill("Tarjeta Bancaria", bankCardToString(), orderID,total);
    }

    private Boolean validaciones() {
        if (!choiceBox_Sucursal.getSelectionModel().isEmpty() && !directionTF.getText().isEmpty() && !nameTF.getText().isEmpty() && !codeTF.getText().isEmpty() && !creditCardTF.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    private String bankCardToString(){
        return "Número de tarjeta: " + creditCardTF.getText() + "\n Nombre: " + nameTF.getText();
    }

    @FXML
    void Atras(ActionEvent event) {
        try {
            FXRouter.goTo("Client");
        } catch (IOException e) {
            System.out.print(e);
        }
    }
}
