package Controller;

import Model.ConnectionDB;
import Model.User;
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
        System.out.print(user + password);
        String userType = ConnectionDB.getInstance().login(user, password);
        if (userType.equals("Empleado")){
            if(User.getCurrentUser().getJob().equals("Gerente")){
                try{
                    FXRouter.goTo("admMenu");
                }catch (IOException e){
                    System.out.print(e);
                }
            }else if(User.getCurrentUser().getJob().equals("Gerente General")){
                try{
                    FXRouter.goTo("admMenu");
                }catch (IOException e){
                    System.out.print(e);
                }
            }else{
                try{
                    FXRouter.goTo("Client");
                }catch (IOException e){
                    System.out.print(e);
                }
            }
        }else if(userType.equals("Cliente")){
            try{
                FXRouter.goTo("Client");
            }catch (IOException e){
                System.out.print(e);
            }
        }else{
            System.out.print("no sirve ??");
        }
    }

    @FXML
    void SignUp(ActionEvent event) {

    }


}
