package Controller;

import Model.ShoppingList_Product;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ComboInfo_Controller {

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



}
