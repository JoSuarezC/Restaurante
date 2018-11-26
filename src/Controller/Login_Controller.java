package Controller;

import Model.ConnectionDB;

import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class Login_Controller {

    @FXML
    private PasswordField TextBox_Password;

    @FXML
    private TextField TextBox_Email;

    @FXML
    void login(ActionEvent event) {
        String user = TextBox_Email.getText();
        String password = TextBox_Password.getText();
        System.out.print(user + password);
        String userType = ConnectionDB.getInstance().login(user, password);
        if (userType.equals("Empleado")){
            if(User.getCurrentUser().getJob().equals("Gerente")){
                try{
                    FXRouter.goTo("MenuAdm");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else if(User.getCurrentUser().getJob().equals("Gerente General")){
                try{
                    FXRouter.goTo("MenuAdm");
                }catch (IOException e){
                    e.printStackTrace();
                }
            }else{
                try{
                    FXRouter.goTo("Client",0);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }else if(userType.equals("Cliente")){
            try{
                FXRouter.goTo("Client",0);
            }catch (IOException e){
                e.printStackTrace();
            }
        }else{
            Main.MessageBox("Credenciales Erróneas", "El usuario y/o la contraseñan son erróneos");
        }
    }

    @FXML
    void SignUp(ActionEvent event) {
        try{
            FXRouter.goTo("SignIn");
        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
