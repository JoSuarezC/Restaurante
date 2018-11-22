/**
 */

package Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AdminMenu_Controller {

    @FXML
    private AnchorPane AnchorPaneMain;

    @FXML
    private Button btn_Reportes;

    @FXML
    private Button btn_Productos;

    @FXML
    private Button btn_Cajero;

    @FXML
    private Button btn_Salir;

    @FXML
    public void GoToProductos(ActionEvent event) {
        try {
            FXRouter.goTo("Products");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    public void GoToManageMenu(ActionEvent event) {
        try {
            FXRouter.goTo("Adm_ManagePMenu");
        } catch (IOException e) {
            System.out.print(e);
        }
    }


    @FXML
    public void GoToPedidosPendientes(ActionEvent event) {
        try {
            FXRouter.goTo("PedidosPendientesAdm");
        } catch (IOException e) {
            System.out.print(e);
        }
    }

    @FXML
    public void GoOutApplication(ActionEvent event) {
        try {
            FXRouter.goTo("Login");
        } catch (IOException e) {   System.out.print(e); }
    }


}
