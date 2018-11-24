package Controller;

import Model.ConnectionDB;
import Model.Product;
import Model.Puesto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminPuestos_Controller {

    @FXML
    private TableColumn<Puesto, String> Column_Puesto;

    @FXML
    private TableColumn<Puesto, String> Column_Descripcion;

    @FXML
    private TextField txt_Nombre;

    @FXML
    private TextField txt_SalarioMaximo;

    @FXML
    private TableView<Puesto> tableView_Puestos;

    @FXML
    private TextField txt_SalarioMinimo;

    @FXML
    private TextArea txt_Descripcion;

    @FXML
    private TableColumn<Puesto, String> Column_Salario1;

    @FXML
    private TableColumn<Puesto, String> Column_Salario2;


    @FXML
    void initialize(){
        txt_Nombre.clear();
        txt_SalarioMaximo.clear();
        txt_SalarioMinimo.clear();
        txt_Descripcion.clear();
        fillTables();
    }

    private void fillTables(){
        Column_Salario2.setCellValueFactory(cellData -> cellData.getValue().salarioMaximoProperty());
        Column_Salario1.setCellValueFactory(cellData -> cellData.getValue().salarioMinimoProperty());
        Column_Descripcion.setCellValueFactory(cellData -> cellData.getValue().detalleProperty());
        Column_Puesto.setCellValueFactory(cellData -> cellData.getValue().nombrePuestoProperty());
        ObservableList<Puesto> puesto_list = FXCollections.observableArrayList();
        puesto_list.addAll(ConnectionDB.getInstance().select_puestos());
        tableView_Puestos.setItems(puesto_list);
    }
    @FXML
    void addJob(ActionEvent event) {
        if(validaciones()){
            if(ConnectionDB.getInstance().createJob(txt_Nombre.getText(), txt_Descripcion.getText(), txt_SalarioMinimo.getText(), txt_SalarioMaximo.getText())){
                Main.MessageBox("Éxito","El puesto se ha agregado con éxito");
                initialize();
            }else{
                Main.MessageBox("Error","Ha ocurrido un error al ingresar el puesto.");
            }
        }
    }

    private Boolean validaciones(){
        return !txt_Nombre.getText().isEmpty() && !txt_Descripcion.getText().isEmpty() && !txt_SalarioMinimo.getText().isEmpty() && !txt_SalarioMaximo.getText().isEmpty();
    }

    @FXML
    void GoBack(ActionEvent event) {

    }
}
