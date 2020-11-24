package Control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;


import java.io.IOException;

public class MainWelcome extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/Welcome.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
