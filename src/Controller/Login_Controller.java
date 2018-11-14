package Controller;

import Model.ConnectionDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Login_Controller {

    @FXML
    private PasswordField TextBox_Password;

    @FXML
    private TextField TextBox_Email;

    @FXML
    private Button Button_Login;

    @FXML
    private Button Button_SignUp;

    @FXML
    void login(ActionEvent event) {
        String user = TextBox_Email.getText();
        String password = TextBox_Password.getText();
        String userType = ConnectionDB.getInstance().login(user, password);
        if (userType.equals("Personal")){
            try{
                FXRouter.goTo("admMenu");
            }catch (IOException e){

            }
        }else if(userType.equals("User")){
            try{
                    FXRouter.goTo("userMenu");
            }catch (IOException e){

            }
        }
    }

    @FXML
    void SignUp(ActionEvent event) {

    }


}
