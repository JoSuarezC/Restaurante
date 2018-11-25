package Controller;

import Model.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Sign_in_Controller {

    @FXML
    private TextField textField_Nombre;

    @FXML
    private TextField textField_Apellidos;

    @FXML
    private TextField textField_Cedula;

    @FXML
    private TextField textField_Telefono;

    @FXML
    private TextField txt_userName;

    @FXML
    private TextField txt_password;

    @FXML
    private TextField txt_correo;

    @FXML
    public void addNewClient(){
        if(textField_Nombre.getText().equals("") || textField_Apellidos.getText().equals("") || textField_Cedula.getText().equals("") || textField_Telefono.getText().equals("")
        || txt_userName.getText().equals("") || txt_password.getText().equals("") || txt_correo.getText().equals("")){
            Main.MessageBox("Error", "Complete todas las casillas para poder realizar el registro correctamente.");
        }
        else{
            int result = ConnectionDB.getInstance().insertClient(textField_Nombre.getText(),textField_Apellidos.getText(),textField_Cedula.getText(),textField_Telefono.getText());
            if(result!=-1){
                boolean response = ConnectionDB.getInstance().createUser(txt_userName.getText(),txt_password.getText(),txt_correo.getText(), Integer.toString(result));
                if(response){
                    Main.MessageBox("Éxito","Se ha registrado correctamente");
                    try {
                        FXRouter.goTo("Login");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Main.MessageBox("Error","No se ha podido realizar el registro correctamente (usuario)");
                }
            }
            else{
                Main.MessageBox("Error", "No se ha podido realizar el registro correctamente (cliente)");
            }

        }
    }

    @FXML
    void GoBack(ActionEvent event) {
        try{
            FXRouter.goTo("Login");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}