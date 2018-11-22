package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;

import java.io.IOException;

public class DetalleProducto_Controller {

    @FXML
    private Text txt_NombreProducto;

    @FXML
    private Text txt_DescripcionProducto;

    @FXML
    private Text txt_PrecioProducto;

    @FXML
    private Text txt_TipoProducto;

    @FXML
    private CheckBox chkBox_Disponible;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnModficar;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    protected void initialize(){
        clearFields();
        showButtonsOptions();
    }

    protected void clearFields(){
        this.txt_NombreProducto.setText(new String(""));
        this.txt_DescripcionProducto.setText(new String(""));
        this.txt_PrecioProducto.setText(new String(""));
        this.txt_TipoProducto.setText(new String(""));
        this.chkBox_Disponible.setSelected(false);
    }

    protected void showButtonsOptions(){
        this.btnAtras.setVisible(true);
        this.btnModficar.setVisible(true);
        this.btnAtras.setDisable(false);
        this.btnModficar.setDisable(false);

        this.btnCancelar.setVisible(false);
        this.btnGuardar.setVisible(false);
        this.btnCancelar.setDisable(true);
        this.btnGuardar.setDisable(true);
    }

    protected void showButtonsState(){
        this.btnCancelar.setVisible(true);
        this.btnGuardar.setVisible(true);
        this.btnCancelar.setDisable(false);
        this.btnGuardar.setDisable(false);

        this.btnAtras.setVisible(false);
        this.btnModficar.setVisible(false);
        this.btnAtras.setDisable(true);
        this.btnModficar.setDisable(true);
    }

    @FXML
    public void atras(ActionEvent event) {
        try {
            FXRouter.goTo("Products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {
        showButtonsOptions();
    }

    @FXML
    public void guardar(ActionEvent event) {

    }

    @FXML
    public void modificar(ActionEvent event) {
        showButtonsState();
    }
}
