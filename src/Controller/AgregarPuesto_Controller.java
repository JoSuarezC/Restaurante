package Controller;

import Model.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AgregarPuesto_Controller {

    @FXML
    private TextField txt_Nombre;

    @FXML
    private TextField txt_SalarioMaximo;

    @FXML
    private TextField txt_SalarioMinimo;

    @FXML
    private TextArea txt_Descripcion;

    @FXML
    private TextField txtPorcentaje;

    @FXML
    void addJob(ActionEvent event) {
        if(validaciones()){
            if(ConnectionDB.getInstance().createJob(txt_Nombre.getText(), txt_Descripcion.getText(), txt_SalarioMinimo.getText(), txt_SalarioMaximo.getText(), txtPorcentaje.getText())){
                Main.MessageBox("Éxito","El puesto se ha agregado con éxito");
                try{
                    FXRouter.goTo("AdmPuestos");
                }catch (IOException e){
                    e.printStackTrace();
                }
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
        try{
            FXRouter.goTo("AdmPuestos");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
