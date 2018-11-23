package Controller;

import Model.ConnectionDB;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TabPane;
import java.io.IOException;

public class Products_Controller {

    private ObservableList<Product> comida_list = FXCollections.observableArrayList();
    private ObservableList<Product> bebidas_list = FXCollections.observableArrayList();
    private ObservableList<Product> dulces_list = FXCollections.observableArrayList();

    @FXML
    private TabPane tabProducts;

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
            e.printStackTrace();
        }
    }

    @FXML
    void CreateCombo(ActionEvent event) {
        try {
            FXRouter.goTo("Adm_CreateCombo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void DetalleProducto(ActionEvent event) {
        try {
            Product temp = null;
            int id;
            switch (this.tabProducts.getSelectionModel().getSelectedItem().getText()){
                case "Secona":
                    if (this.Tabla_Secona.getSelectionModel().getSelectedIndex()==-1){
                        Main.MessageBox("Advertencia","Debe de seleccionar un producto para poder ver su detalle");
                    }
                    else{
                        id = this.Tabla_Secona.getSelectionModel().getSelectedIndex();
                        temp = this.bebidas_list.get(id);
                        FXRouter.goTo("DetalleProducto", temp);
                    }
                    break;
                case "Dulcillo":
                    if (this.Tabla_Dulcillo.getSelectionModel().getSelectedIndex()==-1){
                        Main.MessageBox("Advertencia","Debe de seleccionar un producto para poder ver su detalle");
                    }
                    else{
                        id = this.Tabla_Dulcillo.getSelectionModel().getSelectedIndex();
                        temp = this.dulces_list.get(id);
                        FXRouter.goTo("DetalleProducto", temp);
                    }
                    break;
                case "Monchona":
                    if (this.Tabla_Monchona.getSelectionModel().getSelectedIndex()==-1){
                        Main.MessageBox("Advertencia","Debe de seleccionar un producto para poder ver su detalle");
                    }
                    else{
                        id = this.Tabla_Monchona.getSelectionModel().getSelectedIndex();
                        temp = this.comida_list.get(id);
                        FXRouter.goTo("DetalleProducto", temp);
                    }
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Atras(ActionEvent event) {
        try {
            FXRouter.goTo("MenuAdm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize() {
        fillTables();
    }

    private void fillTables(){
        // Comida
        ClmNombre_tbl_Monchona.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        ClmPrecio_tbl_Monchona.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ClmDescripcion_tbl_Monchona.setCellValueFactory(cellData -> cellData.getValue().productDetailProperty());
        comida_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Comida"));
        Tabla_Monchona.setItems(comida_list);

        // Bebida
        ClmNombre_tbl_Secona.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        ClmPrecio_tbl_Secona.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ClmDescripcion_tbl_Secona.setCellValueFactory(cellData -> cellData.getValue().productDetailProperty());
        bebidas_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Bebida"));
        Tabla_Secona.setItems(bebidas_list);

        // Dulces
        ClmNombre_tbl_Dulcillo.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        ClmPrecio_tbl_Dulcillo.setCellValueFactory(cellData -> cellData.getValue().productPrizeProperty().asString());
        ClmDescripcion_tbl_Dulcillo.setCellValueFactory(cellData -> cellData.getValue().productDetailProperty());
        dulces_list.addAll(ConnectionDB.getInstance().selectProductInventory_byType("Postre"));
        Tabla_Dulcillo.setItems(dulces_list);
    }
}