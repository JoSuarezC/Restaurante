package Controller;

import Model.ConnectionDB;
import Model.ShoppingList_Product;
import Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminManageProdMenu_Controller {


    @FXML
    void createCombo(ActionEvent event) {
        /*
        if(!tablaView_Inventario.getItems().isEmpty()){
            Date fecha = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m");
            String orderID;
            String totalAPagar = Label_TotalCost.getText();
            if (User.getCurrentUser().getUserType().equals("Empleado")){
                orderID = ConnectionDB.getInstance().makeOrder(null, dateFormat.format(fecha).toString(), "Local", totalAPagar);
            }else{
                orderID = ConnectionDB.getInstance().makeOrder(User.getCurrentUser().getUserID(), dateFormat.format(fecha).toString(), "Local", totalAPagar);
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
        }else{
            Main.MessageBox("Tabla de productos vacía", "Seleccione los productos que desea comprar.");
        }*/
    }

}
