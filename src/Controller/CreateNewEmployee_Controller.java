package Controller;

import Model.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import Model.Puesto;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import java.io.IOException;

public class CreateNewEmployee_Controller {

    ObservableList<String> puestosList = FXCollections.observableArrayList("Gerente General", "Gerente", "Cajero", "Salonero", "Cocinero", "Chef");

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_apellidos;

    @FXML
    private TextField txt_cedula;

    @FXML
    private ComboBox<String> cbx_puesto;

    @FXML
    private TextField txt_cuentaBanco;

    @FXML
    private TextField txt_salarioNeto;

    @FXML
    private TextField txt_salarioBruto;

    @FXML
    private TextField txt_nombreUsuario;

    @FXML
    private TextField txt_correo;

    @FXML
    void back(ActionEvent event) {
        try {
            FXRouter.goTo("MenuAdm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize(){
        fillcbx_puestos();
    }

    private void fillcbx_puestos(){
        cbx_puesto.setItems(puestosList);
        cbx_puesto.getSelectionModel().selectFirst();
    }

    @FXML
    private void CrearEmpleado(){

    }
}
