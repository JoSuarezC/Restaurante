package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

public class Products_Controller {

    @FXML
    private Tab Tab_Secona;

    @FXML
    private TableView<?> Tabla_Secona;

    @FXML
    private TableColumn<?, ?> ClmNombre_tbl_Secona;

    @FXML
    private TableColumn<?, ?> ClmDescripcion_tbl_Secona;

    @FXML
    private TableColumn<?, ?> ClmStock_tbl_Secona;

    @FXML
    private TableColumn<?, ?> ClmPrecio_tbl_Secona;

    @FXML
    private Tab Tab_Dulcillo;

    @FXML
    private TableView<?> Tabla_Dulcillo;

    @FXML
    private TableColumn<?, ?> ClmNombre_tbl_Dulcillo;

    @FXML
    private TableColumn<?, ?> ClmDescripcion_tbl_Dulcillo;

    @FXML
    private TableColumn<?, ?> ClmPrecio_tbl_Dulcillo;

    @FXML
    private Tab Tab_Monchona;

    @FXML
    private TableView<?> Tabla_Monchona;

    @FXML
    private TableColumn<?, ?> ClmNombre_tbl_Monchona;

    @FXML
    private TableColumn<?, ?> ClmDescripcion_tbl_Monchona;

    @FXML
    private TableColumn<?, ?> ClmPrecio_tbl_Monchona;

    @FXML
    private Button Button_AgregarProducto;

    @FXML
    private Button Button_Atras;

    @FXML
    public void AddProduct(){
        try {
            FXRouter.goTo("AddProduct");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

}