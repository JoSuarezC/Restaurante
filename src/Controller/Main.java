package Controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setResizable(false);
        FXRouter.bind(this, primaryStage);
        FXRouter.when("Login", "/View/Login.fxml", "Login", 1204, 666);
        FXRouter.goTo("Login");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
