package Controller;

import Model.ConnectionDB;
import Model.Email;
import Model.Puesto;
import Model.Sucursal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javax.mail.internet.InternetAddress;
import java.io.IOException;

public class CreateNewEmployee_Controller {

    @FXML
    private TextField txt_nombre;

    @FXML
    private TextField txt_apellidos;

    @FXML
    private TextField txt_cedula;

    @FXML
    private ComboBox<Puesto> cbx_puesto;

    @FXML
    private ComboBox<Sucursal> cbx_sucursal;

    @FXML
    private TextField txt_cuentaBanco;

    @FXML
    private TextField txt_salario;

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
        ObservableList<Puesto> puestosList = FXCollections.observableArrayList();
        puestosList.addAll(ConnectionDB.getInstance().select_puestos());
        cbx_puesto.setItems(puestosList);
        cbx_puesto.getSelectionModel().selectFirst();

        ObservableList<Sucursal> sucursales_list = FXCollections.observableArrayList();
        sucursales_list.addAll(ConnectionDB.getInstance().selectSucursales());
        cbx_sucursal.setItems(sucursales_list);
        cbx_sucursal.getSelectionModel().selectFirst();
    }

    @FXML
    private void CrearEmpleado(){
        if(validarEntradas()){
            String IdEmpleado = ConnectionDB.getInstance().insertEmpleado(txt_nombre.getText(), txt_apellidos.getText(), txt_cedula.getText(), cbx_puesto.getSelectionModel().getSelectedItem().getIdPuesto(), txt_cuentaBanco.getText(), txt_salario.getText());
            if(IdEmpleado != null){
                System.out.print("Empleado "+IdEmpleado);
                ConnectionDB.getInstance().createUser(txt_nombreUsuario.getText(), "25916319", txt_correo.getText(), IdEmpleado);
                try{
                    Email.sendEmail(new InternetAddress(txt_correo.getText()),"Cuenta Restaurante AK7","Su cuenta para ingresar al Restaurante AK7 se ha geneado con éxito. \r\n Los credenciales a ingresar son: \r\n Usuario: "+txt_nombreUsuario.getText() + "\r\n Contraseña: " + "25916319" + "\r\n Recuerde cambiar su contraseña cuando inicie sesión.");
                }catch (Exception e){}

            }
        }else{
            Main.MessageBox("Error", "Complete todas las casillas para poder crear el empleado.");
        }
    }

    private boolean validarEntradas(){
        return !txt_nombre.getText().isEmpty() && !txt_apellidos.getText().isEmpty() && !txt_cedula.getText().isEmpty() && !txt_cuentaBanco.getText().isEmpty() &&
                !txt_salario.getText().isEmpty() && !txt_nombreUsuario.getText().isEmpty() && !txt_correo.getText().isEmpty();
    }
}
