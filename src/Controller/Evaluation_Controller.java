package Controller;

import Model.ConnectionDB;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evaluation_Controller {

    @FXML
    private TextArea TextAreaOpcional;

    @FXML
    private ComboBox<String> ComboBox2;

    @FXML
    private ComboBox<String> ComboBox1;

    @FXML
    private ComboBox<String> ComboBox3;


    @FXML
    protected void initialize() {
        ComboBox1.getItems().setAll("1","2","3","4","5","6","7","8","9","10");
        ComboBox2.getItems().setAll("1","2","3","4","5","6","7","8","9","10");
        ComboBox3.getItems().setAll("1","2","3","4","5","6","7","8","9","10");
    }

    @FXML
    void sendEvaluation(ActionEvent event){
        if(validaciones()){
            Date fecha = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd H:m");
            ConnectionDB.getInstance().sendEvaluation(dateFormat.format(fecha), User.getCurrentUser().getUserID(), "", TextAreaOpcional.getText(), ComboBox1.getSelectionModel().getSelectedItem(), ComboBox3.getSelectionModel().getSelectedItem(),  ComboBox2.getSelectionModel().getSelectedItem());
        }
    }


    private Boolean validaciones(){
        return !ComboBox1.getSelectionModel().isEmpty() && !ComboBox2.getSelectionModel().isEmpty() && !ComboBox3.getSelectionModel().isEmpty();
    }
}
