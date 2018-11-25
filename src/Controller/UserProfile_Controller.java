package Controller;

import Model.ConnectionDB;
import Model.User;
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
        this.textField_Pass.setDisable(true);
        this.textField_Telefono.setDisable(true);

        this.textField_Nombre.setVisible(false);
        this.textField_Apellidos.setVisible(false);
        this.textField_Cedula.setVisible(false);
        this.textField_Correo.setVisible(false);
        this.textField_Celular.setVisible(false);
        this.textField_Pass.setVisible(false);
        this.textField_Telefono.setVisible(false);
    }

    private void showInfo(Hashtable<String, String> usuario){
        this.txtNombre.setText(usuario.get("Nombre"));
        this.txtApellidos.setText(usuario.get("Apellidos"));
        this.txtCedula.setText(usuario.get("Cedula"));
        this.txtCorreo.setText(usuario.get("Correo"));
        this.txtCelular.setText(usuario.get("Celular"));
        this.txtTelefono.setText(usuario.get("Auxiliar"));
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
        this.textField_Pass.setVisible(false);
    }

    protected void ponerValores(){
        this.textField_Nombre.setText(this.txtNombre.getText());
        this.textField_Apellidos.setText(this.txtApellidos.getText());
        this.textField_Cedula.setText(this.txtCedula.getText());
        this.textField_Correo.setText(this.txtCorreo.getText());
        this.textField_Telefono.setText(this.txtTelefono.getText());
        this.textField_Celular.setText(this.txtCelular.getText());
        this.textField_Pass.setText(this.txtPassword.getText());
    }

    private void activarEspacios(){
        this.txtNombre.setVisible(false);
        this.txtApellidos.setVisible(false);
        this.txtCedula.setVisible(false);
        this.txtCorreo.setVisible(false);
        this.txtTelefono.setVisible(false);
        this.txtCelular.setVisible(false);
        this.txtPassword.setVisible(false);

        this.txtUsername.setVisible(true);

        this.textField_Nombre.setVisible(true);
        this.textField_Apellidos.setVisible(true);
        this.textField_Cedula.setVisible(true);
        this.textField_Correo.setVisible(true);
        this.textField_Telefono.setVisible(true);
        this.textField_Celular.setVisible(true);
        this.textField_Pass.setVisible(true);

        this.textField_Nombre.setDisable(false);
        this.textField_Apellidos.setDisable(false);
        this.textField_Cedula.setDisable(false);
        this.textField_Correo.setDisable(false);
        this.textField_Telefono.setDisable(false);
        this.textField_Celular.setDisable(false);
        this.textField_Pass.setDisable(false);
    }

    private void showButtonsState(){
        this.btn_Cancelar.setVisible(true);
        this.btn_Guardar.setVisible(true);
        this.btn_Cancelar.setDisable(false);
        this.btn_Guardar.setDisable(false);

        this.btn_Atras.setVisible(false);
        this.btn_Modificar.setVisible(false);
        this.btn_Atras.setDisable(true);
        this.btn_Modificar.setDisable(true);
    }

    public void actualizarCurrentUser(){
        User.getCurrentUser().setName(this.textField_Nombre.getText());
        User.getCurrentUser().setLastnames(this.textField_Apellidos.getText());
        User.getCurrentUser().setCedula(this.textField_Cedula.getText());
        User.getCurrentUser().setUser_Email(this.textField_Correo.getText());
        User.getCurrentUser().setCelular(this.textField_Celular.getText());
        User.getCurrentUser().setTelefono(this.textField_Telefono.getText());
        User.getCurrentUser().setPassword(this.textField_Pass.getText());
    }

    public void actualizarDatos(){
        this.usuario.replace("Nombre", this.textField_Nombre.getText());
        this.usuario.replace("Apellidos", this.textField_Apellidos.getText());
        this.usuario.replace("Cedula", this.textField_Cedula.getText());
        this.usuario.replace("Correo", this.textField_Correo.getText());
        this.usuario.replace("Celular", this.textField_Celular.getText());
        this.usuario.replace("Auxiliar", this.textField_Telefono.getText());
        this.usuario.replace("Password", this.textField_Pass.getText());
    }

    @FXML
    private void GoBack(ActionEvent event) {
        try{
            FXRouter.loadPreviousRoute();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        desactivarEspacios();
        showButtonsOptions();
    }

    @FXML
    private void guardar(ActionEvent event) {
        try{
            if (this.textField_Nombre.getText().isEmpty() ||
                    this.textField_Apellidos.getText().isEmpty() ||
                    this.textField_Cedula.getText().isEmpty() ||
                    this.textField_Correo.getText().isEmpty() ||
                    this.textField_Celular.getText().isEmpty() ||
                    this.textField_Telefono.getText().isEmpty() ||
                    this.textField_Pass.getText().isEmpty()) {
                Main.MessageBox("Advertencia", "Debe de llenar todos los campos para poder guardar los cambios");
            }else{
                ConnectionDB.getInstance().updateUser(
                        this.textField_Nombre.getText(),
                        this.textField_Apellidos.getText(),
                        this.textField_Cedula.getText(),
                        this.textField_Correo.getText(),
                        this.textField_Celular.getText(),
                        this.textField_Telefono.getText(),
                        this.textField_Pass.getText(),
                        User.getCurrentUser().getUserID());
                Main.MessageBox("Informacion", "El producto ha sido actualizado correctamente");
                actualizarCurrentUser();
                actualizarDatos();
                desactivarEspacios();
                showButtonsOptions();
                showInfo(this.usuario);
            }
        }catch (Exception e){
            Main.MessageBox("Advertencia","Durante la operación se presento un eroror, intentelo de nuevo");
            System.out.println(e);
        }
    }

    @FXML
    private void modify(ActionEvent event) {
        try{
            ponerValores();
            activarEspacios();
            showButtonsState();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}