package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientOrderHistory_Controller {
    @FXML
    private TableView<?> tbl_historialPedidos;

    @FXML
    private TableColumn<?, ?> clm_fechaHora;

    @FXML
    private TableColumn<?, ?> clm_Productos;

    @FXML
    private TableColumn<?, ?> clm_totalAPagar;

    @FXML
    private TableColumn<?, ?> clm_Estado;

    @FXML
    protected void initialize(){
        fillTable();
    }

    private void fillTable(){
        //clm_fechaHora.setCellValueFactory();
    }
}

