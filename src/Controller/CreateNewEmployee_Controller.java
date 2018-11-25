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
import java.util.ArrayList;

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
            ArrayList<Puesto> array = ConnectionDB.getInstance().select_puestos();
            if(validarSalario(array)){
                String IdEmpleado = ConnectionDB.getInstance().insertEmpleado(txt_nombre.getText(), txt_apellidos.getText(), txt_cedula.getText(), cbx_puesto.getSelectionModel().getSelectedItem().getIdPuesto(), txt_cuentaBanco.getText(), txt_salario.getText());
                if(IdEmpleado != null){
                    System.out.print("Empleado "+IdEmpleado);
                    if (ConnectionDB.getInstance().createUser(txt_nombreUsuario.getText(), "25916319", txt_correo.getText(), IdEmpleado)){
                        try{
                            Email.sendEmail(new InternetAddress(txt_correo.getText()),"Cuenta Restaurante AK7","Su cuenta para ingresar al Restaurante AK7 se ha geneado con éxito. \r\n Los credenciales a ingresar son: \r\n Usuario: "+txt_nombreUsuario.getText() + "\r\n Contraseña: " + "25916319" + "\r\n Recuerde cambiar su contraseña cuando inicie sesión.");
                        }catch (Exception e){e.printStackTrace();}
                        Main.MessageBox("Se ha creado el usuario", "El usuario se ha creado con éxito.");
                        try {
                            FXRouter.goTo("MenuAdm");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Main.MessageBox("Error","El usuario indicado ya existe en la base de datos");
                    }

                }
            }else{Main.MessageBox("Salario inválido", "El salario indicado no se encuentra dentro de los rangos salariales para el puesto asignado");}
        }else{
            Main.MessageBox("Error", "Complete todas las casillas para poder crear el empleado.");
        }
    }

    private boolean validarEntradas(){
        return !txt_nombre.getText().isEmpty() && !txt_apellidos.getText().isEmpty() && !txt_cedula.getText().isEmpty() && !txt_cuentaBanco.getText().isEmpty() &&
                !txt_salario.getText().isEmpty() && !txt_nombreUsuario.getText().isEmpty() && !txt_correo.getText().isEmpty();
    }

    private boolean validarSalario(ArrayList<Puesto> arrayList){
        for (Puesto p : arrayList){
            if (cbx_puesto.getSelectionModel().getSelectedItem().getIdPuesto().equals(p.getIdPuesto())){
                return (Integer.parseInt(p.getSalarioMinimo()) <= Integer.parseInt(txt_salario.getText())) && (Integer.parseInt(p.getSalarioMaximo()) >= Integer.parseInt(txt_salario.getText()));
            }
        }
        return false;
    }
}
