package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import java.util.Hashtable;

public class UserProfile_Controller {
    Hashtable<String,String> usuario;

    @FXML
    private Button btn_Modificar;

    @FXML
    private Button btn_Atras;

    @FXML
    private TextField textField_Nombre;

    @FXML
    private TextField textField_Apellidos;

    @FXML
    private TextField textField_Cedula;

    @FXML
    private TextField textField_Celular;

    @FXML
    private TextField textField_User;

    @FXML
    private TextField textField_Pass;

    @FXML
    private TextField textField_Correo;

    @FXML
    private TextField textField_Telefono;

    @FXML
    private Text txtNombre;

    @FXML
    private Text txtApellidos;

    @FXML
    private Text txtCedula;

    @FXML
    private Text txtCelular;

    @FXML
    private Text txtUsername;

    @FXML
    private Text txtPassword;

    @FXML
    private Text txtCorreo;

    @FXML
    private Text txtTelefono;

    @FXML
    private Button btn_Guardar;

    @FXML
    private Button btn_Cancelar;

    @FXML
    protected void initialize(){
        this.usuario = (Hashtable<String, String>) FXRouter.getData();
        clearFields();
        desactivarEspacios();
        showInfo(this.usuario);
        showButtonsOptions();
    }

    private void clearFields(){
        this.txtNombre.setText(new String(""));
        this.txtApellidos.setText(new String(""));
        this.txtCedula.setText(new String(""));
        this.txtCorreo.setText(new String(""));
        this.txtCelular.setText(new String(""));
        this.txtUsername.setText(new String(""));
        this.txtPassword.setText(new String(""));
        this.txtTelefono.setText(new String(""));

        this.textField_Nombre.setText(new String(""));
        this.textField_Apellidos.setText(new String(""));
        this.textField_Cedula.setText(new String(""));
        this.textField_Correo.setText(new String(""));
        this.textField_Celular.setText(new String(""));
        this.textField_User.setText(new String(""));
        this.textField_Pass.setText(new String(""));
        this.textField_Telefono.setText(new String(""));
    }

    private void desactivarEspacios(){
        this.txtNombre.setVisible(true);
        this.txtApellidos.setVisible(true);
        this.txtCedula.setVisible(true);
        this.txtCorreo.setVisible(true);
        this.txtCelular.setVisible(true);
        this.txtUsername.setVisible(true);
        this.txtPassword.setVisible(true);
        this.txtTelefono.setVisible(true);

        this.textField_Nombre.setDisable(true);
        this.textField_Apellidos.setDisable(true);
        this.textField_Cedula.setDisable(true);
        this.textField_Correo.setDisable(true);
        this.textField_Celular.setDisable(true);
        this.textField_User.setDisable(true);
        this.textField_Pass.setDisable(true);
        this.textField_Telefono.setDisable(true);

        this.textField_Nombre.setVisible(false);
        this.textField_Apellidos.setVisible(false);
        this.textField_Cedula.setVisible(false);
        this.textField_Correo.setVisible(false);
        this.textField_Celular.setVisible(false);
        this.textField_User.setVisible(false);
        this.textField_Pass.setVisible(false);
        this.textField_Telefono.setVisible(false);
    }

    private void showInfo(Hashtable<String, String> usuario){
        this.txtNombre.setText(usuario.get("Nombre"));
        this.txtApellidos.setText(usuario.get("Apellidos"));
        this.txtCedula.setText(usuario.get("Cedula"));
        this.txtCorreo.setText(usuario.get("Correo"));
        this.txtCelular.setText(usuario.get("Celular"));
        this.txtTelefono.setText(usuario.get("Auxilar"));
        this.txtUsername.setText(usuario.get("User"));
        this.txtPassword.setText(usuario.get("Password"));
    }

    private void showButtonsOptions(){
        this.btn_Atras.setVisible(true);
        this.btn_Modificar.setVisible(true);
        this.btn_Atras.setDisable(false);
        this.btn_Modificar.setDisable(false);

        this.btn_Cancelar.setVisible(false);
        this.btn_Guardar.setVisible(false);
        this.btn_Cancelar.setDisable(true);
        this.btn_Guardar.setDisable(true);

        this.textField_Nombre.setVisible(false);
        this.textField_Apellidos.setVisible(false);
        this.textField_Cedula.setVisible(false);
        this.textField_Correo.setVisible(false);
        this.textField_Celular.setVisible(false);
        this.textField_Telefono.setVisible(false);
        this.textField_User.setVisible(false);
        this.textField_Pass.setVisible(false);
    }

    @FXML
    private void GoBack(ActionEvent event) {

    }

    @FXML
    private void cancelar(ActionEvent event) {

    }

    @FXML
    private void guardar(ActionEvent event) {

    }

    @FXML
    private void modify(ActionEvent event) {

    }

}