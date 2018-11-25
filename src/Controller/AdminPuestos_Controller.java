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

import java.io.IOException;

public class AdminPuestos_Controller {

    @FXML
    private TableColumn<Puesto, String> Column_Puesto;

    @FXML
    private TableColumn<Puesto, String> Column_Descripcion;

    @FXML
    private TableView<Puesto> tableView_Puestos;

    @FXML
    private TableColumn<Puesto, String> Column_Salario1;

    @FXML
    private TableColumn<Puesto, String> Column_Salario2;

    @FXML
    private TableColumn<Puesto, String> Column_Comision;

    @FXML
    void initialize(){
        fillTables();
    }

    private void fillTables(){
        Column_Salario2.setCellValueFactory(cellData -> cellData.getValue().salarioMaximoProperty());
        Column_Salario1.setCellValueFactory(cellData -> cellData.getValue().salarioMinimoProperty());
        Column_Descripcion.setCellValueFactory(cellData -> cellData.getValue().detalleProperty());
        Column_Puesto.setCellValueFactory(cellData -> cellData.getValue().nombrePuestoProperty());
        Column_Comision.setCellValueFactory(cellData -> cellData.getValue().comisionProperty());
        ObservableList<Puesto> puesto_list = FXCollections.observableArrayList();
        puesto_list.addAll(ConnectionDB.getInstance().select_puestos());
        tableView_Puestos.setItems(puesto_list);
    }


    @FXML
    void addJob(ActionEvent event) {
        try {
            FXRouter.goTo("AgregarPuesto");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void GoBack(ActionEvent event) {
        try{
            FXRouter.goTo("MenuAdm");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
