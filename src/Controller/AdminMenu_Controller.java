
package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AdminMenu_Controller {

    @FXML
    public void GoToProductos(ActionEvent event) {
        try {
            FXRouter.goTo("Products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void GoToReportes(ActionEvent event) {
        try {
            FXRouter.goTo("Reports");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void GoToManageMenu(ActionEvent event) {
        try {
            FXRouter.goTo("Products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void GoToPedidosPendientes(ActionEvent event) {
        try {
            FXRouter.goTo("PedidosPendientesAdm");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void GoOutApplication(ActionEvent event) {
        try {
            FXRouter.goTo("Login");
        }catch (IOException e){e.printStackTrace();};
    }


    @FXML
    public void Cajero(ActionEvent event) {
        try {
            FXRouter.goTo("Client",0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
