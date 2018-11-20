package Controller;

import Model.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewProduct_Controller {

    @FXML
    private Button btn_Agregar;

    @FXML
    private Button btn_Atras;

    @FXML
    private TextField txt_Nombre;

    @FXML
    private ComboBox<String> cbx_Tipo;

    @FXML
    private TextArea txt_Descripcion;

    @FXML
    private TextField txt_Precio;

    @FXML
    protected void initialize(){
        cbx_Tipo.getItems().addAll("Bebida", "Comida", "Postre");
    }

    @FXML
    public void addProduct(ActionEvent event){
        if(txt_Nombre.getText().equals("") || txt_Descripcion.getText().equals("") || txt_Precio.getText().equals("")){
            Main.MessageBox("Error","Complete todas las casillas para poder agregar el nuevo producto.");
        }
        else{
            boolean result = ConnectionDB.getInstance().searchProduct(txt_Nombre.getText());
            if (result){
                boolean response = ConnectionDB.getInstance().insertProduct(txt_Nombre.getText(),cbx_Tipo.getSelectionModel().getSelectedItem(),txt_Descripcion.getText(),txt_Precio.getText());
                if(response){
                    Main.MessageBox("Éxito","Se ha agregado el producto correctamente.");
                }
                else{
                    Main.MessageBox("Error","No se ha podido agregar el producto al sistema.");
                }
            }
            else{
                Main.MessageBox("Error","El producto ya existe en el sistema.");
            }
        }
    }
}