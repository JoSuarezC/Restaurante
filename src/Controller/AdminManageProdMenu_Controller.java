package Controller;

import Model.ConnectionDB;
import Model.ShoppingList_Product;
import Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdminManageProdMenu_Controller {


    @FXML
    public void GoToCombo(ActionEvent event) {
        try {
            FXRouter.goTo("Adm_CreateCombo");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

}
