package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void atras(ActionEvent event) {
        try {
            FXRouter.goTo("Products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cancelar(ActionEvent event) {

    }

    @FXML
    public void guardar(ActionEvent event) {

    }

    @FXML
    public void modificar(ActionEvent event) {

    }
}
