package Controller;

import Model.Combo;
import Model.ShoppingList_Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ComboInfo_Controller {

    Combo comboInfo = null;

    @FXML
    private TableView<ShoppingList_Product> tbl_infoCombo;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_producto;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_detalle;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_cantidad;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_precioUnit;

    @FXML
    private TableColumn<ShoppingList_Product, String> clm_precioTotal;

    @FXML
    protected void initialize(){
        //comboInfo = FXRouter.getData();
    }

    public void GoBack(ActionEvent event){
        try {
            FXRouter.loadPreviousRoute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
