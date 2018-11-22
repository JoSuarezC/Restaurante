package Controller;

import Model.ConnectionDB;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;

public class Products_Controller {

    @FXML
    private TableView<Product> Tabla_Secona;

    @FXML
    private TableColumn<Product, String> ClmNombre_tbl_Secona;

    @FXML
    private TableColumn<Product, String> ClmDescripcion_tbl_Secona;

    @FXML
    private TableColumn<Product, String> ClmPrecio_tbl_Secona;

    @FXML
    private TableView<Product> Tabla_Dulcillo;

    @FXML
    private TableColumn<Product, String> ClmNombre_tbl_Dulcillo;

    @FXML
    private TableColumn<Product, String> ClmDescripcion_tbl_Dulcillo;

    @FXML
    private TableColumn<Product, String> ClmPrecio_tbl_Dulcillo;

    @FXML
    private TableView<Product> Tabla_Monchona;

    @FXML
    private TableColumn<Product, String> ClmNombre_tbl_Monchona;

    @FXML
    private TableColumn<Product, String> ClmDescripcion_tbl_Monchona;

    @FXML
    private TableColumn<Product, String> ClmPrecio_tbl_Monchona;


    @FXML
    public void AddProduct(ActionEvent event){
        try {
            FXRouter.goTo("AddProduct");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    void CreateCombo(ActionEvent event) {

    }

    @FXML
    protected void initialize(){

        fillTables();

    }

    private void fillTables(){
        // Comida
        ClmNombre_tbl_Monchona.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        ClmPrecio_tbl_Monchona.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ClmDescripcion_tbl_Monchona.setCellValueFactory(cellData -> cellData.getValue().productDetailProperty());
        ObservableList<Product> comida_list = FXCollections.observableArrayList();
        comida_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Comida"));
        Tabla_Monchona.setItems(comida_list);

        // Bebida
        ClmNombre_tbl_Secona.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        ClmPrecio_tbl_Secona.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ClmDescripcion_tbl_Secona.setCellValueFactory(cellData -> cellData.getValue().productDetailProperty());
        ObservableList<Product> bebidas_list = FXCollections.observableArrayList();
        bebidas_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Bebida"));
        Tabla_Secona.setItems(bebidas_list);

        // Dulces
        ClmNombre_tbl_Dulcillo.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        ClmPrecio_tbl_Dulcillo.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ClmDescripcion_tbl_Dulcillo.setCellValueFactory(cellData -> cellData.getValue().productDetailProperty());
        ObservableList<Product> dulces_list = FXCollections.observableArrayList();
        dulces_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Postre"));
        Tabla_Dulcillo.setItems(dulces_list);
    }


}