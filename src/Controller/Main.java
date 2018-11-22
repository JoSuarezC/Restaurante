package Controller;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        FXRouter.bind(this, primaryStage);
        FXRouter.when("Login", "/View/Login.fxml", "Login", 1204, 666);
        FXRouter.when("Client", "/View/Client/ClientOrder.fxml", "Client Order", 1204, 666);
        FXRouter.when("MenuAdm","/View/Administrator/AdminMenu.fxml","Admin Menu",1204,666);
        FXRouter.when("Products","/View/Products.fxml","Products",1204,666);
        FXRouter.when("AddProduct","/View/NewProduct.fxml","Products",1204,666);
        FXRouter.when("SignIn","/View/Sign_in.fxml","Sign In",1204,666);
        FXRouter.when("ClientOrderHistory","/View/Client/ClientOrderHistory.fxml","Order History",1204,666);
        FXRouter.when("OnlinePayment", "/View/Client/ClientOnlinePayment.fxml","Información de pago",1290,690);
        FXRouter.when("LocalPayment", "/View/Client/ClientLocalPayment.fxml","Pago por tarjeta bancaria",1290,690);
        FXRouter.when("PedidosPendientesAdm","/View/Administrator/PedidosPendientesAdm.fxml","Pedidos Pendientes",1204,666);
        FXRouter.goTo("Login");

    }

    public static void MessageBox(String tittle, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tittle);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
