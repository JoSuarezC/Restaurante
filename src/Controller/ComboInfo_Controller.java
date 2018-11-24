package Controller;

import Model.Combo;
import Model.ConnectionDB;
import Model.Product;
import Model.ShoppingList_Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class ComboInfo_Controller {

    ObservableList<ShoppingList_Product> listaProductosCombo = FXCollections.observableArrayList();
    private Combo comboInfo = null;

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
        comboInfo = (Combo)FXRouter.getData();
        fillTable();
    }

    private void fillTable(){
        clm_producto.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        clm_detalle.setCellValueFactory(cellData -> cellData.getValue().productDetailProperty());
        clm_cantidad.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductQuantity())));
        clm_precioUnit.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        clm_precioTotal.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().getProductPrize()*cellData.getValue().getProductQuantity())));
        listaProductosCombo.addAll(comboInfo.getListaProductos());
        tbl_infoCombo.setItems(listaProductosCombo);
    }

    public void GoBack(ActionEvent event){
        try {
            FXRouter.loadPreviousRoute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
